package com.thelazybattley.data.network.response.question

data class QuestionResponse(
    val id: Int,
    val question: String,
    val answer: String,
    val choices: List<String>,
    val type: String
)
