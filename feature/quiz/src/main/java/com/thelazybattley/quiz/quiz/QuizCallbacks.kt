package com.thelazybattley.quiz.quiz

interface QuizCallbacks {

    fun observeTimer()

    fun nextQuestion()

    fun selectAnswer(chosenAnswer: String)

    fun fetchQuestions()

    fun checkQuiz()
}
