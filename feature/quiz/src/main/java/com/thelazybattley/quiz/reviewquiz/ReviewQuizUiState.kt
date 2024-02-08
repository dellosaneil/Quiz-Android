package com.thelazybattley.quiz.reviewquiz

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.quiz.quiz.QuizDetailsState

data class ReviewQuizUiState(
    val throwable: Throwable? = null,
    val quizDetailsState: QuizDetailsState? = null
) : BaseUiState
