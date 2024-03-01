package com.thelazybattley.feedback.createquestion

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.domain.network.usecase.AddQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateQuestionViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val addQuestionUseCase: AddQuestionUseCase
) : BaseViewModel<CreateQuestionEvent, CreateQuestionUiState>(), CreateQuestionCallbacks {

    override fun initialState() = CreateQuestionUiState()

    override fun updateQuestion(text: String) {
        updateState { state ->
            state.copy(
                question = text
            )
        }
    }

    override fun updateChoices(text: String, index: Int) {
        updateState { state ->
            val updatedChoices = state.choices.toMutableList()
            updatedChoices[index] = text
            state.copy(
                choices = updatedChoices
            )
        }
    }

    override fun submitQuestion() {
        viewModelScope.launch(context = dispatcher) {
            val state = getCurrentState()
            addQuestionUseCase(
                answer = state.choices[0]!!,
                question = state.question,
                choices = state.choices.map { it!! },
                category = state.category,
                quizType = state.quizType!!
            ).fold(
                onSuccess = {
                    updateState { _ ->
                        CreateQuestionUiState()
                    }
                },
                onFailure = {

                }
            )
        }
    }

    override fun setQuizType(quizType: QuizType) {
        updateState { state ->
            state.copy(
                quizType = quizType
            )
        }
    }

    override fun setCategory(category: String) {
        updateState { state ->
            state.copy(
                category = category
            )
        }
    }

}
