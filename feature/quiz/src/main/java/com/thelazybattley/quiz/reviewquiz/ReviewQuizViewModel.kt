package com.thelazybattley.quiz.reviewquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.domain.network.usecase.InsertReportedQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewQuizViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle,
    private val insertReportedQuestionUseCase: InsertReportedQuestionUseCase
) : BaseViewModel<ReviewQuizEvents, ReviewQuizUiState>(), ReviewQuizCallbacks {
    override fun initialState() = ReviewQuizUiState()

    init {
        viewModelScope.launch(dispatcher) {
            val quizDetailsState: QuizDetailsState =
                savedStateHandle[AppScreens.QUIZ_RESULT_STATE] ?: return@launch
            updateState { state ->
                val isAnswersCorrect =
                    quizDetailsState
                        .chosenAnswers
                        .zip(quizDetailsState.answers)
                        .map {
                            it.first == it.second
                        }
                state.copy(
                    quizDetailsState = quizDetailsState.copy(
                        question = quizDetailsState.questions.first()
                    ),
                    isAnswersCorrect = isAnswersCorrect,
                    progress = 1f / quizDetailsState.questions.size
                )
            }
        }
    }

    override fun jumpToQuestion(index: Int) {
        updateState { state ->
            val progress = index.toFloat().inc() / state.quizDetailsState?.questions?.size!!
            state.copy(
                currentIndex = index,
                progress = progress,
                quizDetailsState = state.quizDetailsState.copy(
                    question = state.quizDetailsState.questions[index]
                ),
                reportAnswerState = ReportAnswerState()
            )
        }
    }

    override fun showReportAnswerDialog(showDialog: Boolean) {
        updateState { state ->
            state.copy(
                reportAnswerState = ReportAnswerState(
                    showReportDialog = showDialog
                )
            )
        }
    }

    override fun updateTextField(text: String) {
        updateState { state ->
            state.copy(
                reportAnswerState = state.reportAnswerState.copy(
                    text = text
                )
            )
        }
    }

    override fun reportQuestion() {
        viewModelScope.launch(context = dispatcher) {
            val currentQuestion = getCurrentState().quizDetailsState?.question ?: return@launch
            insertReportedQuestionUseCase(
                question = currentQuestion.question,
                questionId = currentQuestion.questionId,
                suggestedAnswer = getCurrentState().reportAnswerState.text,
                quizType = QuizType.LIFE_OF_RIZAL
            ).fold(
                onSuccess = {
                    updateState { state ->
                        state.copy(
                            reportAnswerState = ReportAnswerState()
                        )
                    }
                    emitEvent(event = ReviewQuizEvents.ReportAnswer.Success)
                },
                onFailure = {
                    emitEvent(event = ReviewQuizEvents.ReportAnswer.Error)
                }
            )
        }
    }

    override fun onResetEvent() {
        viewModelScope.launch {
            emitEvent(event = ReviewQuizEvents.Reset)
        }
    }
}
