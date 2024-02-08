package com.thelazybattley.quiz.reviewquiz

import com.thelazybattley.common.base.BaseUiState

data class ReviewQuizUiState(
    val throwable: Throwable? = null
) : BaseUiState
