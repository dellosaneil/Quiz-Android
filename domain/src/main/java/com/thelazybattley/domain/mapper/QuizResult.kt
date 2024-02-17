package com.thelazybattley.domain.mapper

import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.domain.model.QuizResult

val QuizResult.toQuizDetailsState
    get() = run {
        QuizDetailsState(
            questions = questions,
            chosenAnswers = chosenAnswers,
            answers = answers
        )
    }
