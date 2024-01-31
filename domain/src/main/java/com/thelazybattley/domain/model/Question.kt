package com.thelazybattley.domain.model

import com.thelazybattley.common.enums.QuestionType

data class Question(
    val id: Int,
    val question: String,
    val answer: String,
    val choices: List<String>,
    val type: QuestionType
)
