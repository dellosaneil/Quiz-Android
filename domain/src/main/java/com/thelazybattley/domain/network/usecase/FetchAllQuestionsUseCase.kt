package com.thelazybattley.domain.network.usecase

import com.thelazybattley.domain.model.Question

interface FetchAllQuestionsUseCase {

    suspend operator fun invoke(): Result<List<Question>>

}
