package com.thelazybattley.dashboard.dashboard

import com.thelazybattley.common.enums.QuizType

interface DashboardCallbacks {

    fun initialize(quizType: QuizType)
}
