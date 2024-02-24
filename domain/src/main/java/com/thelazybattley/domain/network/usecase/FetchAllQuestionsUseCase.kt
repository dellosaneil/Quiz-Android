package com.thelazybattley.domain.network.usecase

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.Question

interface FetchAllQuestionsUseCase {

    suspend operator fun invoke(quizType: QuizType): Result<List<Question>>

}
