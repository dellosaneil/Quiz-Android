package com.thelazybattley.data.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.Question
import com.thelazybattley.data.local.entity.QuestionEntity

val QuestionEntity.toData
    get() = run {
        Question(
            questionId = id,
            question = question,
            answer = answer,
            choices = choices,
            category = category,
            quizType = quizType
        )
    }

val Question.toEntity
    get() = run {
        QuestionEntity(
            id = questionId,
            question = question,
            answer = answer,
            choices = choices,
            category = category,
            quizType = quizType
        )
    }

fun DocumentSnapshot.toQuestion(quizType: QuizType) = run {
    val questionsArray = get("questions") as? List<Map<String, Any>>
        ?: emptyList()
    val questionsList = questionsArray.map { questionMap ->
        val id = questionMap["questionId"] as Long
        val question = questionMap["question"] as String
        val choices = questionMap["choices"] as List<String>
        val answer = questionMap["answer"] as String
        val category = questionMap["category"] as String
        Question(
            questionId = id.toInt(),
            question = question,
            choices = choices,
            answer = answer,
            category = category,
            quizType = quizType
        )
    }
    questionsList
}
