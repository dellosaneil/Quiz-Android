package com.thelazybattley.domain.local

import com.thelazybattley.common.model.Question

interface GetAllQuestionsUseCase {

    suspend operator fun invoke(count: Int): Result<List<Question>>

}
