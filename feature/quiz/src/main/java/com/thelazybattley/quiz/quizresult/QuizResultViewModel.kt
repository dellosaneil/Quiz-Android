package com.thelazybattley.quiz.quizresult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.model.AppScreens.Companion.QUIZ_RESULT_STATE
import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.domain.local.InsertQuizResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class QuizResultViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle,
    private val insertQuizResultUseCase: InsertQuizResultUseCase,
) : BaseViewModel<QuizResultEvents, QuizResultUiState>(), QuizResultCallbacks {

    override fun initialState() = QuizResultUiState()

    init {
        val quizDetailsState: QuizDetailsState? =
            savedStateHandle[QUIZ_RESULT_STATE]
        viewModelScope.launch(context = dispatcher) {
            if (quizDetailsState == null) {
                return@launch
            }
            updateState { state ->
                val correctAnswers =
                    quizDetailsState.answers.zip(quizDetailsState.chosenAnswers).count {
                        it.first == it.second
                    }
                val incorrectAnswers = quizDetailsState.questions.size - correctAnswers
                val percentage =
                    ((correctAnswers).toFloat() / quizDetailsState.questions.size) * 100
                state.copy(
                    questionDetailsState = quizDetailsState,
                    correctAnswers = correctAnswers,
                    incorrectAnswers = incorrectAnswers,
                    percentage = percentage.roundToInt(),
                    totalQuestions = quizDetailsState.questions.size
                )
            }
        }
        viewModelScope.launch(context = dispatcher) {
            if (quizDetailsState == null) {
                return@launch
            }
            insertQuizResultUseCase(quizDetailsState = quizDetailsState)
        }
    }

    override fun onCloseButtonClicked() {
        emitEvent(event = QuizResultEvents.OnCloseButtonClickedEvent)
    }

    override fun onReviewQuestionsClicked() {
        emitEvent(event = QuizResultEvents.OnReviewQuestionsClicked(quizDetailsState = getCurrentState().questionDetailsState!!))
    }

}
