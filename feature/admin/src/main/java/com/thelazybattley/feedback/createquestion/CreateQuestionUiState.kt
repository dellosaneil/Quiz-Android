package com.thelazybattley.feedback.createquestion

import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.common.enums.QuizType

data class CreateQuestionUiState(
    val question: String = "",
    val choices: List<String?> = listOf(null, null, null, null),
    val quizType: QuizType? = null,
    val category: String = ""
) : BaseUiState
