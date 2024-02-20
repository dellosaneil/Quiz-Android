package com.thelazybattley.quiz.quizconfig

import com.thelazybattley.common.base.BaseEvents

sealed class QuizConfigEvents : BaseEvents {

    data class StartQuiz(val category: String?, val count: Int) : QuizConfigEvents()
}
