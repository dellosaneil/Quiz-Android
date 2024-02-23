package com.thelazybattley.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: Int,
    val question: String,
    val answer: String,
    val choices: List<String>,
    val category: String
) : Parcelable
