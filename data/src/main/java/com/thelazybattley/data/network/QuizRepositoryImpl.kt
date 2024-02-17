package com.thelazybattley.data.network

import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.model.Question
import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.data.local.dao.QuestionsDao
import com.thelazybattley.data.local.dao.QuizResultDao
import com.thelazybattley.data.local.entity.QuizResultEntity
import com.thelazybattley.data.mapper.toData
import com.thelazybattley.data.mapper.toEntity
import com.thelazybattley.data.network.payload.QuestionPayload
import com.thelazybattley.data.network.payload.ReportQuestionPayload
import com.thelazybattley.data.network.service.QuizService
import com.thelazybattley.domain.model.QuizResult
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val service: QuizService,
    private val questionsDao: QuestionsDao,
    private val quizResultDao: QuizResultDao
) : QuizRepository {

    override suspend fun fetchAllQuestions() = runCatching {
        service.fetchQuestions().map { response ->
            response.toData
        }
    }

    override suspend fun addQuestion(
        question: String,
        answer: String,
        choices: List<String>,
        type: QuestionCategory
    ) = runCatching {
        service.addQuestion(
            payload = QuestionPayload(
                question = question,
                answer = answer,
                choices = choices,
                category = type.name
            )
        ).toData
    }

    override suspend fun insertReportedQuestion(
        suggestedAnswer: String,
        questionId: Int,
        question: String
    ) = runCatching {
        service.insertReportedQuestion(
            payload = ReportQuestionPayload(
                questionId = questionId,
                suggestedAnswer = suggestedAnswer,
                question = question
            )
        )
    }

    override suspend fun fetchCategoriesDetails() = runCatching {
        service.fetchCategoriesDetails().map { response ->
            response.toData
        }
    }

    override suspend fun getAllQuestions() = runCatching {
        questionsDao.getAll().map { entity ->
            entity.toData.copy(
                choices = entity.choices.shuffled()
            )
        }
    }

    override suspend fun insertAllQuestions(questions: List<Question>) = runCatching {
        questionsDao.insertAll(questions = questions.map { it.toEntity })
    }

    override suspend fun getQuestionsByCategory(category: QuestionCategory, count: Int) =
        runCatching {
            questionsDao.getQuestionsByCategory(category = category)
                .map { entity ->
                    entity.toData.copy(
                        choices = entity.choices.shuffled()
                    )
                }
                .shuffled()
                .take(count)
        }

    override suspend fun getAllQuizResult() = runCatching {
        quizResultDao
            .getAll().map { entity ->
                QuizResult(
                    questions = entity.questions.map { it.toData },
                    answers = entity.answers,
                    chosenAnswers = entity.chosenAnswers
                )
            }
            .asReversed()
    }

    override suspend fun clearAllQuizResult() = runCatching {
        quizResultDao.clearAll()
    }

    override suspend fun insertQuizResult(
        quizDetailsState: QuizDetailsState
    ) = runCatching {
        quizResultDao.insertResult(
            quizResultEntity = QuizResultEntity(
                questions = quizDetailsState.questions.map { it.toEntity },
                chosenAnswers = quizDetailsState.chosenAnswers,
                answers = quizDetailsState.answers
            )
        )
    }
}
