package com.thelazybattley.data.network

import com.thelazybattley.data.network.service.QuizService
import com.thelazybattley.domain.model.Question
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val service: QuizService
) : QuizRepository {
    override suspend fun fetchQuestions(): Result<List<Question>> {
        return try {
            val questions = service.fetchQuestions().map {
                Question(
                    id = it.id,
                    answer = it.answer,
                    question = it.question
                )
            }
            Result.success(questions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
