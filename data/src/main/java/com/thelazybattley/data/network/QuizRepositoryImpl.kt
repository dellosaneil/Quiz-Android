package com.thelazybattley.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.ext.toTitleCase
import com.thelazybattley.common.model.Question
import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.data.local.dao.QuestionsDao
import com.thelazybattley.data.local.dao.QuizResultDao
import com.thelazybattley.data.local.entity.AnsweredQuestionEntity
import com.thelazybattley.data.local.entity.QuizResultEntity
import com.thelazybattley.data.mapper.toData
import com.thelazybattley.data.mapper.toEntity
import com.thelazybattley.data.mapper.toQuestion
import com.thelazybattley.data.mapper.toReportedQuestions
import com.thelazybattley.domain.model.QuizResult
import com.thelazybattley.domain.model.ReportedQuestion
import com.thelazybattley.domain.network.QuizRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random

class QuizRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val questionsDao: QuestionsDao,
    private val quizResultDao: QuizResultDao
) : QuizRepository {

    override suspend fun fetchAllQuestions(
        quizType: QuizType
    ) = runCatching {
        firestore
            .collection("quiz")
            .document(quizType.type)
            .get()
            .await()
            .toQuestion(quizType = quizType)
    }

    override suspend fun addQuestion(
        question: String,
        answer: String,
        choices: List<String>,
        category: String,
        quizType: QuizType
    ): Result<Unit> = runCatching {
        val updatedQuestions = fetchAllQuestions(quizType = quizType)
            .getOrThrow()
            .toMutableList()
        if (updatedQuestions.isEmpty()) {
            return Result.failure(exception = Exception("List is Empty"))
        }
        updatedQuestions.add(
            element = Question(
                questionId = Random.nextInt(),
                answer = answer,
                category = category.toTitleCase(),
                choices = choices,
                question = question,
                quizType = quizType
            )
        )
        firestore
            .collection("quiz")
            .document(quizType.type)
            .update(
                mapOf("questions" to updatedQuestions)
            )
            .await()

    }

    override suspend fun insertReportedQuestion(
        suggestedAnswer: String,
        questionId: Int,
        question: String,
        quizType: QuizType
    ) = runCatching {
        val updatedReportedQuestions =
            fetchReportedQuestions(quizType = quizType).getOrThrow().toMutableList()
        val reportedQuestion = ReportedQuestion(
            questionId = questionId,
            question = question,
            suggestedAnswer = suggestedAnswer
        )
        updatedReportedQuestions.add(reportedQuestion)
        firestore
            .collection("report")
            .document(quizType.type)
            .update(mapOf("questions" to updatedReportedQuestions))
            .await()
        Unit
    }

    override suspend fun fetchReportedQuestions(quizType: QuizType) =
        runCatching {
            firestore
                .collection("report")
                .document(quizType.type)
                .get()
                .await()
                .toReportedQuestions
        }

    override suspend fun getAllQuestions(
        count: Int,
        quizType: QuizType,
        filtered: Boolean
    ): Result<List<Question>> = runCatching {
        val answeredQuestions = getAllAnsweredQuestions()
        questionsDao.getAllQuestions(quizType = quizType)
            .shuffled()
            .filterNot {
                if (filtered) {
                    answeredQuestions.contains(it.id)
                } else {
                    false
                }
            }
            .take(count)
            .map { entity ->
                entity
                    .toData
                    .copy(
                        choices = entity.choices.shuffled()
                    )
            }
    }

    override suspend fun insertAllQuestions(questions: List<Question>) = runCatching {
        questionsDao.insertQuestions(questions = questions.map { it.toEntity })
    }

    override suspend fun getQuestionsByCategory(category: String, count: Int, quizType: QuizType) =
        runCatching {
            val answeredQuestions = getAllAnsweredQuestions()
            val allQuestions =
                questionsDao
                    .getQuestionsByCategory(category = category, quizType = quizType)
                    .map { entity ->
                        entity.toData.copy(
                            choices = entity.choices.shuffled()
                        )
                    }.shuffled()

            val filteredQuestions = allQuestions
                    .filterNot { answeredQuestions.contains(element = it.questionId) }
                    .take(n = count)
                    .toMutableList()

            if (filteredQuestions.size == count) {
                return@runCatching filteredQuestions
            }
            deleteAnsweredQuestions(questionIds = allQuestions.map { it.questionId })
            filteredQuestions.addAll(allQuestions)
            filteredQuestions.distinct().take(n = count)
        }

    override suspend fun getAllQuizResult() = runCatching {
        quizResultDao.getAll().map { entity ->
            val category =
                if (entity.questions.all { it.category == entity.questions.first().category }) {
                    entity.questions.first().category.toTitleCase()
                } else {
                    "All"
                }

            val correctAnswers = entity.chosenAnswers.zip(entity.answers).filter {
                it.first == it.second
            }.size
            val percentage = (correctAnswers.toFloat() / entity.questions.size) * 100

            QuizResult(
                questions = entity.questions.map { it.toData },
                answers = entity.answers,
                chosenAnswers = entity.chosenAnswers,
                category = category,
                percent = percentage.roundToInt(),
                type = entity.questions.first().quizType
            )
        }.asReversed()
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

    override suspend fun insertAnsweredQuestion(questionIds: List<Int>) = runCatching {
        questionIds.forEach { questionId ->
            questionsDao.insertAnsweredQuestion(
                answeredQuestionEntity = AnsweredQuestionEntity(id = questionId)
            )
        }
    }

    override suspend fun getAllAnsweredQuestions() =
        questionsDao.getAllAnsweredQuestions().map { it.id }

    override suspend fun deleteAnsweredQuestions(questionIds: List<Int>) =
        questionsDao.deleteAnsweredQuestions(questionIds = questionIds)

}
