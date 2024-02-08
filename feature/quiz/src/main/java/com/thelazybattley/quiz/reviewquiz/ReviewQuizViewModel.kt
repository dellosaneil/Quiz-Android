package com.thelazybattley.quiz.reviewquiz

import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ReviewQuizViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<ReviewQuizEvents, ReviewQuizUiState>(), ReviewQuizCallbacks {
    override fun initialState() = ReviewQuizUiState()
}
