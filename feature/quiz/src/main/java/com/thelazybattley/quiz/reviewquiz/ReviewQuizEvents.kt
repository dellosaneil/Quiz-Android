package com.thelazybattley.quiz.reviewquiz

import com.thelazybattley.common.base.BaseEvents

sealed class ReviewQuizEvents : BaseEvents {

    sealed class ReportAnswer : ReviewQuizEvents() {
        data object Success : ReportAnswer()
        data object Error : ReportAnswer()
    }

    data object Reset : ReviewQuizEvents()
}
