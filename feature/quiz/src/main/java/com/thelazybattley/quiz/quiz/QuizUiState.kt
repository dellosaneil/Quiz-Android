package com.thelazybattley.quiz.quiz

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.thelazybattley.common.base.BaseUiState
import com.thelazybattley.domain.model.Question
import com.thelazybattley.quiz.quiz.QuizViewModel.Companion.TOTAL_TIME
import kotlinx.parcelize.Parcelize

data class QuizUiState(
    val throwable: Throwable? = null,
    val timerState: TimerState = TimerState(),
    val currentNumber: Int = -1,
    val isLoading: Boolean = true,
    val currentChosenAnswer: String? = null,
    val isLastQuestion: Boolean = false,
    val quizDetailsState: QuizDetailsState = QuizDetailsState()
) : BaseUiState

data class TimerState(
    val remainingTime: Float = TOTAL_TIME,
    val totalTime: Float = TOTAL_TIME,
    val degrees: Float = 0f
)

@Parcelize
data class QuizDetailsState(
    val questions: List<Question> = emptyList(),
    val chosenAnswers: List<String?> = emptyList(),
    val answers: List<String> = emptyList(),
    val question : Question? = null
) : Parcelable

class AssetParamType : NavType<QuizDetailsState>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): QuizDetailsState? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): QuizDetailsState {
        val clean = value.removeSurrounding(prefix = "{", suffix = "}")
        return Gson().fromJson(clean, QuizDetailsState::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: QuizDetailsState) {
        bundle.putParcelable(key, value)
    }
}
