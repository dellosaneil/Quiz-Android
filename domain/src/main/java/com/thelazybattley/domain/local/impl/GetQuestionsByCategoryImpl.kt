package com.thelazybattley.domain.local.impl

import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.domain.local.GetQuestionsByCategory
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class GetQuestionsByCategoryImpl @Inject constructor(
    private val repository: QuizRepository
) : GetQuestionsByCategory {

    override suspend fun invoke(category: QuestionCategory, count: Int) =
        repository.getQuestionsByCategory(category = category, count = count)
}
