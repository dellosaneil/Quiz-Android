package com.thelazybattley.quiz.quizresultshistory


interface QuizResultsHistoryCallbacks {

    fun onSelectCategory(category: String)

    companion object {
        fun default() = object  : QuizResultsHistoryCallbacks {
            override fun onSelectCategory(category: String) {
                TODO("Not yet implemented")
            }
        }
    }
}
