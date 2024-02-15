package com.thelazybattley.domain.network.usecase

import com.thelazybattley.domain.model.CategoryDetail

interface FetchCategoryDetails {

    suspend operator fun invoke() : Result<List<CategoryDetail>>
}
