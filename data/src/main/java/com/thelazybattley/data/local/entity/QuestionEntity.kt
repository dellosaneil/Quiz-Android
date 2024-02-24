package com.thelazybattley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thelazybattley.common.enums.QuizType

@Entity
data class QuestionEntity(
    @PrimaryKey val id: Int,
    val question: String,
    val answer: String,
    val choices: List<String>,
    val category: String,
    val quizType: QuizType
)
