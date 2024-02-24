package com.thelazybattley.common.model

import android.os.Parcelable
import com.thelazybattley.common.enums.QuizType
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val questionId: Int,
    val question: String,
    val answer: String,
    val choices: List<String>,
    val category: String,
    val quizType: QuizType
) : Parcelable
