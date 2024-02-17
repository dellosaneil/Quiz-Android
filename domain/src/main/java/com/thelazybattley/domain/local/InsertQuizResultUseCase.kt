package com.thelazybattley.domain.local

import com.thelazybattley.common.model.QuizDetailsState

interface InsertQuizResultUseCase {
    suspend operator fun invoke(quizDetailsState: QuizDetailsState) : Result<Unit>
}
