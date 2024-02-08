package com.thelazybattley.quiz.quizresult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.model.AppScreens.Companion.QUIZ_RESULT_STATE
import com.thelazybattley.quiz.quiz.QuizDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class QuizResultViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<QuizResultEvents, QuizResultUiState>(), QuizResultCallbacks {

    override fun initialState() = QuizResultUiState()

    init {
        viewModelScope.launch(context = dispatcher) {
            val questionDetailsState: QuizDetailsState = savedStateHandle[QUIZ_RESULT_STATE] ?: return@launch
            updateState { state ->
                val skippedQuestions = questionDetailsState.chosenAnswers.count { it == null }
                val correctAnswers =
                    questionDetailsState.answers.zip(questionDetailsState.chosenAnswers).count {
                        it.first == it.second
                    }
                val incorrectAnswers = questionDetailsState.questions.size - correctAnswers - skippedQuestions
                val percentage = ((correctAnswers).toFloat() / questionDetailsState.questions.size) * 100
                state.copy(
                    questionDetailsState = questionDetailsState,
                    skippedQuestions =skippedQuestions ,
                    correctAnswers = correctAnswers,
                    incorrectAnswers = incorrectAnswers,
                    percentage = percentage.roundToInt()
                )
            }
        }
    }

    override fun onCloseButtonClicked() {
        emitEvent(event = QuizResultEvents.OnCloseButtonClickedEvent)
    }

}
