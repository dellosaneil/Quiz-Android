package com.thelazybattley.quiz.reviewquiz

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.quiz.quiz.QuizDetailsState

data class ReviewQuizUiState(
    val throwable: Throwable? = null,
    val quizDetailsState: QuizDetailsState? = null,
    val isAnswersCorrect: List<Boolean> = emptyList(),
    val currentIndex: Int = 0,
    val progress: Float = 0f
) : BaseUiState
