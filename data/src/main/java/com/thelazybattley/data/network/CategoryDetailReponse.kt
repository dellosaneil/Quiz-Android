package com.thelazybattley.data.network

import com.thelazybattley.common.enums.toQuestionCategory
import com.thelazybattley.data.network.response.categoriesdetails.CategoryDetailResponse
import com.thelazybattley.domain.model.CategoryDetail

val CategoryDetailResponse.toData
    get() = run {
        CategoryDetail(
            count = count,
            category = category.toQuestionCategory
        )
    }
