package com.thelazybattley.domain.network.usecase

import com.thelazybattley.domain.model.Question

interface FetchQuestionsUseCase {

    suspend operator fun invoke(): Result<List<Question>>

}
