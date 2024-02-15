package com.thelazybattley.domain.local

import com.thelazybattley.domain.model.Question

interface GetAllQuestionsUseCase {

    suspend operator fun invoke() : Result<List<Question>>
}
