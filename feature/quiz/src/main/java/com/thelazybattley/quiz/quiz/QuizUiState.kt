package com.thelazybattley.quiz.quiz

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.quiz.quiz.QuizViewModel.Companion.TOTAL_TIME

data class QuizUiState(
    val throwable: Throwable? = null,
    val timerState: TimerState = TimerState()
) : BaseUiState


data class TimerState(
    val remainingTime: Float = TOTAL_TIME,
    val totalTime: Float = TOTAL_TIME,
    val degrees: Float = 0f
)
