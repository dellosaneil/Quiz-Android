package com.thelazybattley.quiz.quizconfig

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.domain.model.CategoryDetail

data class QuizConfigUiState(
    val selectedCategory: CategoryDetail? = null,
    val count: Int = 0,
    val categories: List<CategoryDetail> = emptyList()
) : BaseUiState
