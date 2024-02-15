package com.thelazybattley.data.network.payload

data class QuestionPayload(
    val question: String,
    val answer: String,
    val choices: List<String>,
    val category: String
)
