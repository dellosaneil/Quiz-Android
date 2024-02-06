package com.thelazybattley.quiz.quiz

import com.thelazybattley.common.base.BaseEvents

sealed class QuizEvents : BaseEvents {

    data class FinishedQuizEvent(
        val quizDetailsState: QuizDetailsState
    ): QuizEvents()

}
