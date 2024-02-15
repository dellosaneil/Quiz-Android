package com.thelazybattley.data.mapper

import com.thelazybattley.common.enums.toQuestionCategory
import com.thelazybattley.data.local.entity.QuestionEntity
import com.thelazybattley.data.network.response.question.QuestionResponse
import com.thelazybattley.domain.model.Question

val QuestionEntity.toData
    get() = run {
        Question(
            id = id,
            question = question,
            answer = answer,
            choices = choices,
            category = category.toQuestionCategory
        )
    }

val QuestionResponse.toData
    get() = run {
        Question(
            id = id,
            question = question,
            answer = answer,
            choices = choices,
            category = category.toQuestionCategory
        )
    }

val Question.toEntity
    get() = run {
        QuestionEntity(
            id = id,
            question = question,
            answer = answer,
            choices = choices,
            category = category.name
        )
    }
