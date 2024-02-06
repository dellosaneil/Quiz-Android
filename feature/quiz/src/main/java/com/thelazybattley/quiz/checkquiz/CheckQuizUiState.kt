package com.thelazybattley.quiz.checkquiz

import com.thelazybattley.common.base.BaseUiState

data class CheckQuizUiState(
    val throwable: Throwable? = null
) : BaseUiState
