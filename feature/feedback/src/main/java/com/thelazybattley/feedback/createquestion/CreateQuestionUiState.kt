package com.thelazybattley.feedback.createquestion

import com.thelazybattley.common.base.BaseUiState

data class CreateQuestionUiState(
    val question: String = "",
    val choices: List<String?> = listOf(null, null, null, null)
) : BaseUiState
