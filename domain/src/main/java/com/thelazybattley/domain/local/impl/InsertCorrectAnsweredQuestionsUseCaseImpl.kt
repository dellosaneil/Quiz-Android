package com.thelazybattley.domain.local.impl

import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.domain.local.InsertCorrectAnsweredQuestionsUseCase
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class InsertCorrectAnsweredQuestionsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : InsertCorrectAnsweredQuestionsUseCase {
    override suspend fun invoke(quizDetailsState: QuizDetailsState) = run {
        val indexes = quizDetailsState
            .answers
            .zip(quizDetailsState.chosenAnswers)
            .mapIndexed { index, pair ->
                if (pair.first == pair.second) {
                    index
                } else {
                    null
                }
            }.filterNotNull()
        val questionIds = indexes
            .map { index -> quizDetailsState.questions[index].questionId }
        repository.insertAnsweredQuestion(questionIds = questionIds)
    }
}
