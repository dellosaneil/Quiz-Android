package com.thelazybattley.domain.local

interface GetAllAnsweredQuestionsUseCase {

    suspend operator fun invoke(): List<Int>
}
