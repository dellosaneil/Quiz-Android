package com.thelazybattley.quiz.quizresultshistory

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.domain.model.QuizResult

data class QuizResultsHistoryUiState(
    val completeQuizResult: List<QuizResult> = emptyList(),
    val filteredQuizResult: List<QuizResult> = emptyList(),
    val selectedCategory: QuestionCategory? = null,
    val categories: List<QuestionCategory> = emptyList()
) : BaseUiState
