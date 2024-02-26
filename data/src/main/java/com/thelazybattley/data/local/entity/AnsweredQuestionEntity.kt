package com.thelazybattley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnsweredQuestionEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val questionId: Int
)
