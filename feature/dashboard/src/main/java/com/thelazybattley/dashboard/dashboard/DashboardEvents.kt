package com.thelazybattley.dashboard.dashboard

import com.thelazybattley.common.base.BaseEvents
import com.thelazybattley.domain.model.QuizResult

sealed class DashboardEvents : BaseEvents {

    data class OpenPreviousQuiz(
        val quizResult: QuizResult
    ) : DashboardEvents()

}
