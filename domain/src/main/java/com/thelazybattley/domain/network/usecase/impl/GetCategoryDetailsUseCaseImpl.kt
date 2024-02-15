package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.domain.network.QuizRepository
import com.thelazybattley.domain.network.usecase.GetCategoryDetailsUseCase
import javax.inject.Inject

class GetCategoryDetailsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : GetCategoryDetailsUseCase {
    override suspend fun invoke() = run {
        val localQuestions = repository.getAllQuestions()
        localQuestions.mapCatching { questions ->
            questions.groupBy { question -> question.category }
        }.map { categorizedQuestions ->
            categorizedQuestions.keys.map { category ->
                CategoryDetail(
                    count = categorizedQuestions[category]?.size ?: 0,
                    category = category
                )
            }

        }
    }
}
