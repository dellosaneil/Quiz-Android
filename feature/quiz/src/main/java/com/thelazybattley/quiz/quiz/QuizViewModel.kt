package com.thelazybattley.quiz.quiz

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.domain.network.usecase.FetchQuestionsUseCase
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
    private val fetchQuestionsUseCase: FetchQuestionsUseCase
) : BaseViewModel<QuizEvents, QuizUiState>(),
    QuizCallbacks {

    private var timerJob: Job? = Job(SupervisorJob())

    companion object {
        const val TIME_PER_QUESTION = 60

    }

    override fun initialState() = QuizUiState()

    init {
        fetchQuestions()
    }

    override fun fetchQuestions() {
        viewModelScope.launch(context = dispatcher) {
            fetchQuestionsUseCase()
                .fold(
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
                                remainingTime = TIME_PER_QUESTION * questions.size
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
            val updatedProgress = (index.toFloat() / state.quizDetailsState.questions.size.dec())
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
