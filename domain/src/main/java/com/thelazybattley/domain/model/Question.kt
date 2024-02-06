package com.thelazybattley.domain.model

import android.os.Parcelable
import com.thelazybattley.common.enums.QuestionType
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: Int,
    val question: String,
    val answer: String,
    val choices: List<String>,
    val type: QuestionType
) : Parcelable
