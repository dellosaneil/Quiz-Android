package com.thelazybattley.quiz.quizconfig

import com.thelazybattley.domain.model.CategoryDetail

interface QuizConfigCallbacks {

    fun selectCategory(category: CategoryDetail)

    fun selectCount(count: Int)

    fun startQuiz()

    fun updateCount(count: String)

    companion object {
        fun default () = object  : QuizConfigCallbacks {
            override fun selectCategory(category: CategoryDetail) {
                TODO("Not yet implemented")
            }

            override fun selectCount(count: Int) {
                TODO("Not yet implemented")
            }

            override fun startQuiz() {
                TODO("Not yet implemented")
            }

            override fun updateCount(count: String) {
                TODO("Not yet implemented")
            }
        }
    }
}
