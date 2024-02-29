package com.thelazybattley.dashboard.dashboard

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.domain.model.QuizResult

data class DashboardUiState(
    val throwable: Throwable? = null,
    val lifeOfRizalCategories: List<CategoryDetail> = emptyList(),
    val noliCategories: List<CategoryDetail> = emptyList(),
    val elFiliCategories: List<CategoryDetail> = emptyList(),
    val quizResultExceeding: Int = 0,
    val quizResults: List<QuizResult> = emptyList(),
    val isLoading: Boolean = true
) : BaseUiState
