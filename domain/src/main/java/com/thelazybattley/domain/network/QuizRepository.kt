package com.thelazybattley.domain.network

import com.thelazybattley.domain.model.Question

interface QuizRepository {

    suspend fun fetchQuestions() : Result<List<Question>>

}
