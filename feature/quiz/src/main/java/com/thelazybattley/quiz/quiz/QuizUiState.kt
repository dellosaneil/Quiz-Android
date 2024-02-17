package com.thelazybattley.quiz.quiz

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.model.QuizDetailsState

data class QuizUiState(
    val throwable: Throwable? = null,
    val currentIndex: Int = -1,
    val isLoading: Boolean = true,
    val quizDetailsState: QuizDetailsState = QuizDetailsState(),
    val progress: Float = 0f,
    val isComplete: Boolean = false,
    val remainingTime: Int = 0,
    val showSubmitButtonDialog: Boolean = false,
    val category: QuestionCategory? = null
) : BaseUiState

