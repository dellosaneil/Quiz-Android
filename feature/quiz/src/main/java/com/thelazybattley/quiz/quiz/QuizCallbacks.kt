package com.thelazybattley.quiz.quiz

interface QuizCallbacks {

    suspend fun observeTimer()

    fun submitQuiz()

    fun selectAnswer(chosenAnswer: String)

    fun fetchQuestions()

    fun goToQuestion(index: Int)
}
