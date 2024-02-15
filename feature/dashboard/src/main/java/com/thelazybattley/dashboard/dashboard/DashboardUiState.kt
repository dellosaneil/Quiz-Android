package com.thelazybattley.dashboard.dashboard

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.domain.model.CategoryDetail

data class DashboardUiState(
    val throwable: Throwable? = null,
    val categoriesDetails: List<CategoryDetail> = emptyList()
) : BaseUiState
