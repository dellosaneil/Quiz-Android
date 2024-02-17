package com.thelazybattley.common.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizDetailsState(
    val questions: List<Question> = emptyList(),
    val chosenAnswers: List<String?> = emptyList(),
    val answers: List<String> = emptyList(),
    val question : Question? = null
) : Parcelable

class QuizDetailsStateParamType : NavType<QuizDetailsState>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): QuizDetailsState? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): QuizDetailsState {
        return Gson().fromJson(value, QuizDetailsState::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: QuizDetailsState) {
        bundle.putParcelable(key, value)
    }
}
