package com.thelazybattley.data.network.service

import com.thelazybattley.data.network.payload.QuestionPayload
import com.thelazybattley.data.network.payload.ReportQuestionPayload
import com.thelazybattley.data.network.response.categoriesdetails.CategoryDetailResponse
import com.thelazybattley.data.network.response.question.QuestionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizService {

    @GET("question/all")
    suspend fun fetchQuestions() : List<QuestionResponse>

    @POST("question/insert")
    suspend fun addQuestion(@Body payload: QuestionPayload) : QuestionResponse

    @POST("/report/insert")
    suspend fun insertReportedQuestion(@Body payload: ReportQuestionPayload)

    @GET("question/categories-details")
    suspend fun fetchCategoriesDetails(): List<CategoryDetailResponse>

}
