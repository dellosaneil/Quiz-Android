package com.thelazybattley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnsweredQuestionEntity(
    @PrimaryKey val id : Int
)
