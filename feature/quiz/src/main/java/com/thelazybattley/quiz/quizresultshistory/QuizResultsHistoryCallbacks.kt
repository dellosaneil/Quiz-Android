package com.thelazybattley.quiz.quizresultshistory

import com.thelazybattley.common.enums.QuestionCategory

interface QuizResultsHistoryCallbacks {

    fun onSelectCategory(category: QuestionCategory)

    companion object {
        fun default() = object  : QuizResultsHistoryCallbacks {
            override fun onSelectCategory(category: QuestionCategory) {
                TODO("Not yet implemented")
            }
        }
    }
}
