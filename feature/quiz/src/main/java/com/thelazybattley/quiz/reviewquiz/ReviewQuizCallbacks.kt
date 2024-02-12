package com.thelazybattley.quiz.reviewquiz

interface ReviewQuizCallbacks {

    fun jumpToQuestion(index: Int)

    fun showReportAnswerDialog(showDialog: Boolean)

    fun updateTextField(text: String)

}
