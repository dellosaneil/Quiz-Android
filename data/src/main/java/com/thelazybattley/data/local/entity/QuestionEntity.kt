package com.thelazybattley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    @PrimaryKey val id: Int,
    val question: String,
    val answer: String,
    val choices: List<String>,
    val category: String
)
