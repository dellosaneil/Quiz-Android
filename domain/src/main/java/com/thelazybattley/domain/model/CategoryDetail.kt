package com.thelazybattley.domain.model

import com.thelazybattley.common.enums.QuestionCategory

data class CategoryDetail(
    val count: Int,
    val category: QuestionCategory
)
