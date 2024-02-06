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
        const val TOTAL_TIME = 60f

    }

    override fun initialState() = QuizUiState()

    init {
        observeTimer()
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
                                    answers = questions.map { it.answer }
                                ),
                                currentNumber = 1,
                            )
                        }
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


    override fun observeTimer() {
        timerJob = viewModelScope.launch(dispatcher) {
            var remainingTime = getCurrentState().timerState.totalTime
            do {
                delay(1000L)
                remainingTime--
                updateState { state ->
                    state.copy(
                        timerState = state.timerState.copy(
                            remainingTime = remainingTime
                        )
                    )
                }
            } while (remainingTime != 0f)
            nextQuestion()
        }
    }

    override fun nextQuestion() {
        timerJob?.cancel()
        inputAnswer()
        observeTimer()
    }

    override fun checkQuiz() {
        timerJob?.cancel()
        inputAnswer()
        viewModelScope.launch {
            emitEvent(
                event = QuizEvents.FinishedQuizEvent(
                    quizDetailsState = getCurrentState().quizDetailsState
                )
            )
        }
    }

    override fun selectAnswer(chosenAnswer: String) {
        updateState { state ->
            state.copy(
                currentChosenAnswer = chosenAnswer
            )
        }
    }

    override fun inputAnswer() {
        if(!getCurrentState().isLastQuestion) {
            updateState { state ->
                state.copy(
                    quizDetailsState = state.quizDetailsState.copy(
                        question = state.quizDetailsState.questions[state.currentNumber]
                    )
                )
            }
        }

        updateState { state ->
            val updatedChosenAnswers = state.quizDetailsState.chosenAnswers.toMutableList()
            updatedChosenAnswers.add(state.currentChosenAnswer)
            val updatedCurrentNumber = state.currentNumber.inc()
            state.copy(
                timerState = state.timerState.copy(
                    remainingTime = TOTAL_TIME
                ),
                currentChosenAnswer = null,
                currentNumber = updatedCurrentNumber,
                quizDetailsState = state.quizDetailsState.copy(
                    chosenAnswers = updatedChosenAnswers
                ),
                isLastQuestion = updatedCurrentNumber == state.quizDetailsState.questions.size
            )
        }
    }
}
