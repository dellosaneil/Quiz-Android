package com.thelazybattley.dashboard.dashboard

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.domain.model.QuizResult

data class DashboardUiState(
    val throwable: Throwable? = null,
    val categoriesDetails: List<CategoryDetail> = emptyList(),
    val quizResults: List<QuizResult> = emptyList(),
    val quizResultExceeding: Int = 0
) : BaseUiState
