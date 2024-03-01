package com.thelazybattley.feedback.createquestion

import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class CreateQuestionViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<CreateQuestionEvent, CreateQuestionUiState>(), CreateQuestionCallbacks {

    override fun initialState() = CreateQuestionUiState()

}
