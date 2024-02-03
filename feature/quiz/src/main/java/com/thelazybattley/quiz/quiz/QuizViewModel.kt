package com.thelazybattley.quiz.quiz

import com.thelazybattley.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : BaseViewModel<QuizEvents, QuizUiState>(),
    QuizCallbacks {
    override fun initialState() = QuizUiState()
}
