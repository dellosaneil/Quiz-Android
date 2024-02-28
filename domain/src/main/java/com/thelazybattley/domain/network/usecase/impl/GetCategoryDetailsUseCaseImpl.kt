package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.ext.toTitleCase
import com.thelazybattley.domain.local.GetAllAnsweredQuestionsUseCase
import com.thelazybattley.domain.local.GetAllQuestionsUseCase
import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.domain.network.usecase.GetCategoryDetailsUseCase
import javax.inject.Inject

class GetCategoryDetailsUseCaseImpl @Inject constructor(
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase,
    private val getAnsweredQuestionsUseCase: GetAllAnsweredQuestionsUseCase
) : GetCategoryDetailsUseCase {
    override suspend fun invoke(quizType: QuizType) = run {
        val answeredQuestions = getAnsweredQuestionsUseCase()
        val localQuestions = getAllQuestionsUseCase(
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
                val categories = categorizedQuestions[category]
                val answeredCount =
                    categories?.count { answeredQuestions.contains(element = it.questionId) } ?: 0
                val count = categories?.size ?: 0
                CategoryDetail(
                    count = count,
                    category = category.toTitleCase(),
                    answeredCount = answeredCount,
                    progress = answeredCount.toFloat() / count
                )
            }
        }
    }
}
