package com.thelazybattley.feedback.createquestion

import com.thelazybattley.common.enums.QuizType

interface CreateQuestionCallbacks {

    fun updateQuestion(text: String)
    fun updateChoices(text: String, index: Int)
    fun submitQuestion()
    fun setQuizType(quizType: QuizType)
    fun setCategory(category: String)

    companion object {
        fun default() = object : CreateQuestionCallbacks {
            override fun updateQuestion(text: String) {
                TODO("Not yet implemented")
            }

            override fun updateChoices(text: String, index: Int) {
                TODO("Not yet implemented")
            }

            override fun submitQuestion() {
                TODO("Not yet implemented")
            }

            override fun setQuizType(quizType: QuizType) {
                TODO("Not yet implemented")
            }

            override fun setCategory(category: String) {
                TODO("Not yet implemented")
            }

        }
    }
}
