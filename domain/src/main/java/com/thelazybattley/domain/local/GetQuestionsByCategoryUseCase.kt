package com.thelazybattley.domain.local

import com.thelazybattley.common.model.Question

interface GetQuestionsByCategoryUseCase {

    suspend operator fun invoke(category: String, count: Int) : Result<List<Question>>

}
