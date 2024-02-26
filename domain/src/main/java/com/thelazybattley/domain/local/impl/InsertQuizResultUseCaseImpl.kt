package com.thelazybattley.domain.local.impl

import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.domain.local.InsertQuizResultUseCase
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class InsertQuizResultUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : InsertQuizResultUseCase {

    override suspend fun invoke(quizDetailsState: QuizDetailsState) = repository.insertQuizResult(quizDetailsState = quizDetailsState)
}
