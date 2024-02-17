package com.thelazybattley.domain.local

import com.thelazybattley.domain.model.QuizResult

interface GetAllQuizResultsUseCase {

    suspend operator fun invoke() : Result<List<QuizResult>>
}
