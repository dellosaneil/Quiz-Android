package com.thelazybattley.quiz.quiz

import com.thelazybattley.common.base.BaseEvents
import com.thelazybattley.common.model.QuizDetailsState

sealed class QuizEvents : BaseEvents {

    data class FinishedQuizEvent(
        val quizDetailsState: QuizDetailsState
    ): QuizEvents()

}
