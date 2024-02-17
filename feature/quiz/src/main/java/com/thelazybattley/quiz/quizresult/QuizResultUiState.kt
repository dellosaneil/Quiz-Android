package com.thelazybattley.quiz.quizresult

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.common.model.QuizDetailsState

data class QuizResultUiState(
    val questionDetailsState: QuizDetailsState? = null,
    val percentage: Int = 0,
    val correctAnswers: Int = 0,
    val incorrectAnswers: Int = 0,
    val totalQuestions : Int = 0
) : BaseUiState
