package com.thelazybattley.quiz.reviewquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.quiz.quiz.QuizDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewQuizViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ReviewQuizEvents, ReviewQuizUiState>(), ReviewQuizCallbacks {
    override fun initialState() = ReviewQuizUiState()

    init {
        viewModelScope.launch(dispatcher) {
            val quizDetailsState: QuizDetailsState = savedStateHandle[AppScreens.QUIZ_RESULT_STATE] ?: return@launch
            updateState { state ->
                state.copy(
                    quizDetailsState = quizDetailsState
                )
            }
        }
    }
}
