package com.thelazybattley.domain.local.impl

import com.thelazybattley.domain.local.GetQuestionsByCategoryUseCase
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class GetQuestionsByCategoryUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : GetQuestionsByCategoryUseCase {

    override suspend fun invoke(category: String, count: Int) =
        repository.getQuestionsByCategory(category = category, count = count)
}
