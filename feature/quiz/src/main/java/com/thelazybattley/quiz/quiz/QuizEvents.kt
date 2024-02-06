package com.thelazybattley.quiz.quiz

import com.thelazybattley.common.base.BaseEvents

sealed class QuizEvents : BaseEvents {

    data object CheckQuizEvent: QuizEvents()

}
