package com.thelazybattley.domain.network.usecase

import com.thelazybattley.common.model.Question

interface FetchAllQuestionsUseCase {

    suspend operator fun invoke(): Result<List<Question>>

}
