package com.thelazybattley.data.network.payload

data class ReportQuestionPayload(
    val questionId: Int,
    val suggestedAnswer: String,
    val question: String
)
