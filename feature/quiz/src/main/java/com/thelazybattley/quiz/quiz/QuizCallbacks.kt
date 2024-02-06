package com.thelazybattley.quiz.quiz

interface QuizCallbacks {

    fun observeTimer()

    fun nextQuestion()

    fun selectAnswer(index: Int)
}
