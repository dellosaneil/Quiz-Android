package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.domain.network.QuizRepository
import com.thelazybattley.domain.network.usecase.FetchCategoryDetails
import javax.inject.Inject

class FetchCategoryDetailsImpl @Inject constructor(
    private val repository: QuizRepository
) : FetchCategoryDetails {
    override suspend fun invoke() = repository.fetchCategoriesDetails()
}
