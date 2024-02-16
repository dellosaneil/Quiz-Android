package com.thelazybattley.quiz.quizconfig

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.enums.toQuestionCategory
import com.thelazybattley.common.model.AppScreens.Companion.QUIZ_CATEGORY
import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.domain.network.usecase.GetCategoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizConfigViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getCategoryDetailsUseCase: GetCategoryDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<QuizConfigEvents, QuizConfigUiState>(), QuizConfigCallbacks {

    override fun initialState() = QuizConfigUiState()

    init {
        getCategoryDetails()
    }

    private fun getCategoryDetails() {
        val category: String? = savedStateHandle[QUIZ_CATEGORY]
        val questionCategory = category?.toQuestionCategory

        viewModelScope.launch(context = dispatcher) {
            getCategoryDetailsUseCase()
                .fold(
                    onSuccess = { categories ->
                        val categoryDetail =
                            categories.firstOrNull { it.category == questionCategory }
                        updateState { state ->
                            state.copy(
                                selectedCategory = categoryDetail,
                                categories = categories
                            )
                        }
                    },
                    onFailure = {

                    }
                )
        }
    }

    override fun selectCategory(category: CategoryDetail) {
        updateState { state ->
            state.copy(
                selectedCategory = category,
                count = 0
            )
        }
    }

    override fun selectCount(count: Int) {
        updateState { state ->
            state.copy(
                count = count
            )
        }
    }

    override fun startQuiz() {
        val selectedCategory = getCurrentState().selectedCategory ?: return
        emitEvent(
            event = QuizConfigEvents.StartQuiz(
                category = selectedCategory.category.name,
                count = getCurrentState().count
            )
        )
    }

    override fun updateCount(count: String) {
        val countNumber = count.toIntOrNull() ?: 0
        val selectedCategory = getCurrentState().selectedCategory ?: return
        val adjustedCount = countNumber.coerceAtMost(maximumValue = selectedCategory.count)
        updateState { state ->
            state.copy(
                count = adjustedCount
            )
        }
    }
}
