package com.thelazybattley.domain.model

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.Question

data class QuizResult(
    val questions: List<Question>,
    val chosenAnswers: List<String?>,
    val answers: List<String>,
    val percent: Int,
    val category: String,
    val type: QuizType
)
