package com.thelazybattley.quiz.reviewquiz

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.common.model.QuizDetailsState

data class ReviewQuizUiState(
    val throwable: Throwable? = null,
    val quizDetailsState: QuizDetailsState? = null,
    val isAnswersCorrect: List<Boolean> = emptyList(),
    val currentIndex: Int = 0,
    val progress: Float = 0f,
    val reportAnswerState: ReportAnswerState = ReportAnswerState()
) : BaseUiState

data class ReportAnswerState(
    val showReportDialog: Boolean = false,
    val text: String = ""
)
