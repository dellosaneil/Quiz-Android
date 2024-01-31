package com.thelazybattley.domain.model

data class Question(
    val id: Int,
    val question: String,
    val answer: String,
    val choices: List<String>
)
