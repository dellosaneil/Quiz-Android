package com.thelazybattley.quiz.quiz

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<QuizEvents, QuizUiState>(),
    QuizCallbacks {

    companion object {
        const val TOTAL_TIME = 10f

    }

    override fun initialState() = QuizUiState()

    init {
        observeTimer()
    }


    override fun observeTimer() {
        viewModelScope.launch(dispatcher) {
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
        updateState { state ->
            state.copy(
                timerState = state.timerState.copy(
                    remainingTime = TOTAL_TIME
                )
            )
        }
    }
}
