package com.thelazybattley.data.network.service

import com.thelazybattley.data.network.response.question.QuestionResponse
import retrofit2.http.GET

interface QuizService {

    @GET("question/all")
    suspend fun fetchQuestions() : List<QuestionResponse>

}
