package com.thelazybattley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val questions: List<QuestionEntity>,
    val chosenAnswers: List<String?>,
    val answers: List<String>
)
