package com.thelazybattley.domain.model

import com.thelazybattley.common.model.Question

data class QuizResult(
    val questions: List<Question> = emptyList(),
    val chosenAnswers: List<String?> = emptyList(),
    val answers: List<String> = emptyList()
)
