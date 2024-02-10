package com.thelazybattley.quiz.quiz

interface QuizCallbacks {

    fun observeTimer()

    fun submitQuiz()

    fun selectAnswer(chosenAnswer: String)

    fun fetchQuestions()

    fun goToQuestion(index: Int)
}
