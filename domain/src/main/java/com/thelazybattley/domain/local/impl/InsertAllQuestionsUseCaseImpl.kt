package com.thelazybattley.domain.local.impl

import com.thelazybattley.common.model.Question
import com.thelazybattley.domain.local.InsertAllQuestionsUseCase
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class InsertAllQuestionsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : InsertAllQuestionsUseCase {

    override suspend fun invoke(questions: List<Question>) =
        repository.insertAllQuestions(questions = questions)
}
