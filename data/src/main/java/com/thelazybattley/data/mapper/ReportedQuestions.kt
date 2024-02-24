package com.thelazybattley.data.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.thelazybattley.domain.model.ReportedQuestion

val DocumentSnapshot.toReportedQuestions
    get() = run {
        val questionsArray = get("questions") as? List<Map<String, Any>>
            ?: emptyList()
        val questionsList = questionsArray.map { questionMap ->
            val id = questionMap["questionId"] as Long
            val question = questionMap["question"] as String
            val suggestedAnswer = questionMap["suggestedAnswer"] as String
            ReportedQuestion(
                questionId = id.toInt(),
                question = question,
                suggestedAnswer = suggestedAnswer
            )
        }
        questionsList
    }
