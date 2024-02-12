package com.thelazybattley.domain.network.usecase

interface InsertReportedQuestionUseCase {

    suspend operator fun invoke(
        question: String,
        questionId: Int,
        suggestedAnswer: String
    ) : Result<Unit>
}
