package com.thelazybattley.data.network

import com.google.firebase.firestore.DocumentReference
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.ext.toTitleCase
import com.thelazybattley.common.model.Question
import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.data.local.dao.QuestionsDao
import com.thelazybattley.data.local.dao.QuizResultDao
import com.thelazybattley.data.local.entity.PermanentAnsweredQuestionEntity
import com.thelazybattley.data.local.entity.QuizResultEntity
import com.thelazybattley.data.local.entity.TempAnsweredQuestionEntity
import com.thelazybattley.data.mapper.toData
import com.thelazybattley.data.mapper.toEntity
import com.thelazybattley.data.mapper.toQuestion
import com.thelazybattley.data.mapper.toReportedQuestions
import com.thelazybattley.domain.model.QuizResult
import com.thelazybattley.domain.model.ReportedQuestion
import com.thelazybattley.domain.network.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random

class QuizRepositoryImpl @Inject constructor(
    private val document: DocumentReference,
    private val questionsDao: QuestionsDao,
    private val quizResultDao: QuizResultDao
) : QuizRepository {

    override suspend fun fetchAllQuestions(
        quizType: QuizType
    ) = runCatching {
        document
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
        document
            .collection("quiz")
            .document(quizType.type)
            .set(
                mapOf("questions" to updatedQuestions)
            )
            .await()
    }

    override suspend fun insertReportedQuestion(
        suggestedAnswer: String,
        questionId: Int,
        question: String,
        quizType: QuizType
    ): Result<Unit> = runCatching {
        val updatedReportedQuestions =
            fetchReportedQuestions(quizType = quizType)
                .getOrThrow()
                .toMutableList()
        val reportedQuestion = ReportedQuestion(
            questionId = questionId,
            question = question,
            suggestedAnswer = suggestedAnswer
        )
        updatedReportedQuestions.add(reportedQuestion)
        document
            .collection("report")
            .document(quizType.type)
            .set(mapOf("questions" to updatedReportedQuestions))
            .await()
    }

    override suspend fun fetchReportedQuestions(quizType: QuizType) =
        runCatching {
            document
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
        val allQuestions = questionsDao
            .getAllQuestions(quizType = quizType)
            .map { entity ->
                entity
                    .toData
                    .copy(
                        choices = entity.choices.shuffled()
                    )
            }
            .shuffled()

        val filteredQuestions = allQuestions
            .filterNot {
                if (filtered) {
                    answeredQuestions.contains(it.questionId)
                } else {
                    false
                }
            }
            .take(count)
            .toMutableList()

        if (filteredQuestions.size == count || !filtered) {
            return@runCatching filteredQuestions
        }
        deleteAnsweredQuestions(questionIds = allQuestions.map { it.questionId })
        filteredQuestions.addAll(allQuestions)

        filteredQuestions.distinct().take(n = count)
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

    override suspend fun getAllQuizResult(): Result<Flow<List<QuizResult>>> = runCatching {
        quizResultDao
            .getAll()
            .map { entity ->
                entity.map { result ->
                    val category =
                        if (result.questions.all { it.category == result.questions.first().category }) {
                            result.questions.first().category.toTitleCase()
                        } else {
                            "All"
                        }

                    val correctAnswers = result.chosenAnswers.zip(result.answers).filter {
                        it.first == it.second
                    }.size
                    val percentage = (correctAnswers.toFloat() / result.questions.size) * 100

                    QuizResult(
                        questions = result.questions.map { it.toData },
                        answers = result.answers,
                        chosenAnswers = result.chosenAnswers,
                        category = category,
                        percent = percentage.roundToInt(),
                        type = result.questions.first().quizType
                    )
                }
            }
            .map { it.asReversed() }

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
            questionsDao.insertTempAnsweredQuestion(
                tempAnsweredQuestionEntity = TempAnsweredQuestionEntity(id = questionId)
            )
            questionsDao.insertPermanentAnsweredQuestion(
                entity = PermanentAnsweredQuestionEntity(id = questionId)
            )
        }
    }

    override suspend fun getAllAnsweredQuestions() =
        questionsDao.getAllPermanentAnsweredQuestions().map { it.id }

    override suspend fun deleteAnsweredQuestions(questionIds: List<Int>) =
        questionsDao.deleteTempAnsweredQuestions(questionIds = questionIds)

}
