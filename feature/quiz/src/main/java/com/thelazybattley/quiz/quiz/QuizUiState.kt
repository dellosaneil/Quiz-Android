package com.thelazybattley.quiz.quiz

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.domain.model.Question
import com.thelazybattley.quiz.quiz.QuizViewModel.Companion.TOTAL_TIME

data class QuizUiState(
    val throwable: Throwable? = null,
    val timerState: TimerState = TimerState(),
    val questions: List<Question> = emptyList(),
    val currentNumber: Int = -1,
    val isLoading: Boolean = true,
    val question: Question? = null,
    val answers: List<String> = emptyList(),
    val chosenAnswers: List<String?> = emptyList(),
    val currentChosenAnswer: String? = null,
    val isEndOfQuiz: Boolean = false
) : BaseUiState


data class TimerState(
    val remainingTime: Float = TOTAL_TIME,
    val totalTime: Float = TOTAL_TIME,
    val degrees: Float = 0f
)
