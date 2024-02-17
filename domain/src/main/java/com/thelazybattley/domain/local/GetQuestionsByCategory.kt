package com.thelazybattley.domain.local

import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.model.Question

interface GetQuestionsByCategory {

    suspend operator fun invoke(category: QuestionCategory, count: Int) : Result<List<Question>>

}
