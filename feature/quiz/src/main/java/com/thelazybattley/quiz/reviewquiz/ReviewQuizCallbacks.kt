package com.thelazybattley.quiz.reviewquiz

interface ReviewQuizCallbacks {

    fun jumpToQuestion(index: Int)

    fun showReportAnswerDialog(showDialog: Boolean)

    fun updateTextField(text: String)

    fun reportQuestion()

    fun onResetEvent()

    companion object {
        fun default() = object : ReviewQuizCallbacks {
            override fun jumpToQuestion(index: Int) {
                TODO("Not yet implemented")
            }

            override fun showReportAnswerDialog(showDialog: Boolean) {
                TODO("Not yet implemented")
            }

            override fun updateTextField(text: String) {
                TODO("Not yet implemented")
            }

            override fun reportQuestion() {
                TODO("Not yet implemented")
            }

            override fun onResetEvent() {
                TODO("Not yet implemented")
            }

        }
    }

}
