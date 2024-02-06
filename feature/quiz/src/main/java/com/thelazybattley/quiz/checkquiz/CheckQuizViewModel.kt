package com.thelazybattley.quiz.checkquiz

import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class CheckQuizViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<CheckQuizEvent, CheckQuizUiState>(), CheckQuizCallbacks {
    override fun initialState() = CheckQuizUiState()
}
