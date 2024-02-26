package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.ext.toTitleCase
import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.domain.network.QuizRepository
import com.thelazybattley.domain.network.usecase.GetCategoryDetailsUseCase
import javax.inject.Inject

class GetCategoryDetailsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : GetCategoryDetailsUseCase {
    override suspend fun invoke(quizType: QuizType) = run {
        val localQuestions = repository.getAllQuestions(
            count = Integer.MAX_VALUE,
            quizType = quizType,
            filtered = false
        )
        localQuestions.mapCatching { questions ->
            questions
                .sortedBy { it.category }
                .groupBy { question -> question.category }
        }.map { categorizedQuestions ->
            categorizedQuestions.keys.map { category ->
                CategoryDetail(
                    count = categorizedQuestions[category]?.size ?: 0,
                    category = category.toTitleCase()
                )
            }
        }
    }
}
