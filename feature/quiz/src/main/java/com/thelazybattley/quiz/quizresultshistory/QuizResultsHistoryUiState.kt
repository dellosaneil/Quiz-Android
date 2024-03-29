package com.thelazybattley.quiz.quizresultshistory

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.domain.model.QuizResult

data class QuizResultsHistoryUiState(
    val completeQuizResult: List<QuizResult> = emptyList(),
    val filteredQuizResult: List<QuizResult> = emptyList(),
    val selectedCategory: String? = null,
    val categories: List<String> = emptyList()
) : BaseUiState
