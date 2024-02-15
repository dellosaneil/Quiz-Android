package com.thelazybattley.domain.network.usecase

import com.thelazybattley.domain.model.CategoryDetail

interface GetCategoryDetailsUseCase {

    suspend operator fun invoke() : Result<List<CategoryDetail>>
}
