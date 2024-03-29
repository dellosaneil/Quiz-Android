package com.thelazybattley.quiz.quiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.domain.local.GetAllQuestionsUseCase
import com.thelazybattley.domain.local.GetQuestionsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getQuestionsByCategoryUseCase: GetQuestionsByCategoryUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase
) : BaseViewModel<QuizEvents, QuizUiState>(),
    QuizCallbacks {

    private var timerJob: Job? = Job(SupervisorJob())

    companion object {
        const val TIME_PER_QUESTION = 60
        const val DEFAULT_QUESTION_COUNT = 10
    }

    override fun initialState() = QuizUiState()

    init {
        fetchQuestions()
    }

    override fun fetchQuestions() {
        val category: String? = savedStateHandle[AppScreens.QUIZ_CATEGORY]
        val count: Int = savedStateHandle[AppScreens.QUESTIONS_COUNT] ?: DEFAULT_QUESTION_COUNT
        val quizType: String = savedStateHandle[AppScreens.QUIZ_TYPE] ?: return

        viewModelScope.launch(context = dispatcher) {
            if (category != null) {
                getQuestionsByCategoryUseCase(
                    category = category,
                    count = count,
                    quizType = QuizType.toQuizType(type = quizType)
                )
            } else {
                getAllQuestionsUseCase(
                    count = count,
                    quizType = QuizType.toQuizType(type = quizType),
                    filtered = true
                )
            }.fold(
                onSuccess = { questions ->
                    updateState { state ->
                        state.copy(
                            quizDetailsState = state.quizDetailsState.copy(
                                questions = questions,
                                question = questions.first(),
                                answers = questions.map { it.answer },
                                chosenAnswers = questions.map { null }
                            ),
                            currentIndex = 0,
                            remainingTime = TIME_PER_QUESTION * questions.size,
                            progress = 1f / questions.size
                        )
                    }
                    observeTimer()
                },
                onFailure = { throwable ->
                    updateState { state ->
                        state.copy(
                            throwable = throwable
                        )
                    }
                }
            )
            updateState { state ->
                state.copy(
                    isLoading = false
                )
            }
        }
    }


    override suspend fun observeTimer() {
        timerJob = viewModelScope.launch(dispatcher) {
            var remainingTime =
                TIME_PER_QUESTION * getCurrentState().quizDetailsState.questions.size
            do {
                delay(1000L)
                remainingTime--
                updateState { state ->
                    state.copy(
                        remainingTime = remainingTime
                    )
                }
            } while (remainingTime != 0)
            submitQuiz()
        }
    }

    override fun submitQuiz() {
        timerJob?.cancel()
        updateState { state ->
            state.copy(
                showSubmitButtonDialog = false
            )
        }
        emitEvent(
            event = QuizEvents.FinishedQuizEvent(
                quizDetailsState = getCurrentState().quizDetailsState
            )
        )
    }

    override fun selectAnswer(chosenAnswer: String) {
        updateState { state ->
            val updatedChosenAnswers = state.quizDetailsState.chosenAnswers.toMutableList()
            updatedChosenAnswers[state.currentIndex] = chosenAnswer
            state.copy(
                quizDetailsState = state.quizDetailsState.copy(
                    chosenAnswers = updatedChosenAnswers
                ),
                isComplete = !updatedChosenAnswers.contains(null)
            )
        }
    }

    override fun goToQuestion(
        index: Int
    ) {
        updateState { state ->
            val updatedProgress = (index.toFloat().inc() / state.quizDetailsState.questions.size)
            state.copy(
                currentIndex = index,
                progress = updatedProgress,
                quizDetailsState = state.quizDetailsState.copy(
                    question = state.quizDetailsState.questions[index]
                )
            )
        }
    }

    override fun updateSubmitDialog(showDialog: Boolean) {
        updateState { state ->
            state.copy(
                showSubmitButtonDialog = showDialog
            )
        }
    }
}
