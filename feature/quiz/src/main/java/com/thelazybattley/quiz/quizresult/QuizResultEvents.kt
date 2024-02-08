package com.thelazybattley.quiz.quizresult

import com.thelazybattley.common.base.BaseEvents

sealed class QuizResultEvents : BaseEvents {

    data object OnCloseButtonClickedEvent: QuizResultEvents()
}
