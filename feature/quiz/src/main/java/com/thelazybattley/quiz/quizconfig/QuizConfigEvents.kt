package com.thelazybattley.quiz.quizconfig

import com.thelazybattley.common.base.BaseEvents
import com.thelazybattley.common.enums.QuizType

sealed class QuizConfigEvents : BaseEvents {

    data class StartQuiz(val category: String?, val count: Int, val quizType: QuizType) : QuizConfigEvents()
}
