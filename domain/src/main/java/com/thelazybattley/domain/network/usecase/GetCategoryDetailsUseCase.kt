package com.thelazybattley.domain.network.usecase

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.domain.model.CategoryDetail

interface GetCategoryDetailsUseCase {

    suspend operator fun invoke(quizType: QuizType) : Result<List<CategoryDetail>>
}
