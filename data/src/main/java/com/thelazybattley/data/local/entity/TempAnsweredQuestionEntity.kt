package com.thelazybattley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TempAnsweredQuestionEntity(
    @PrimaryKey val id : Int
)
