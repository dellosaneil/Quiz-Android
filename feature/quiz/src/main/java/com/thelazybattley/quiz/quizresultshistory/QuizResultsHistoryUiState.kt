package com.thelazybattley.quiz.quizresultshistory

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.domain.model.QuizResult

data class QuizResultsHistoryUiState(
    val quizResult: List<QuizResult> = emptyList(),
    val filterCategory: QuestionCategory? = null
) : BaseUiState
