package com.thelazybattley.quiz.quizconfig

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.AppScreens.Companion.QUIZ_CATEGORY
import com.thelazybattley.common.model.AppScreens.Companion.QUIZ_TYPE
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
        val quizType: String = savedStateHandle[QUIZ_TYPE] ?: QuizType.LIFE_OF_RIZAL.type

        viewModelScope.launch(context = dispatcher) {
            getCategoryDetailsUseCase(quizType = QuizType.toQuizType(type = quizType))
                .fold(
                    onSuccess = { categories ->
                        val categoryDetail =
                            categories.firstOrNull { it.category == category }
                        updateState { state ->
                            state.copy(
                                selectedCategory = categoryDetail,
                                categories = categories,
                                totalQuestions = categories.sumOf { it.count }
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
            val updatedCategory = if (category == state.selectedCategory) null else category
            state.copy(
                selectedCategory = updatedCategory,
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
        val quizType: String = savedStateHandle[QUIZ_TYPE] ?: QuizType.LIFE_OF_RIZAL.type
        emitEvent(
            event = QuizConfigEvents.StartQuiz(
                category = getCurrentState().selectedCategory?.category,
                count = getCurrentState().count,
                quizType = QuizType.toQuizType(type = quizType)
            )
        )
    }

    override fun updateCount(count: String) {
        val countNumber = count.toIntOrNull() ?: 0
        val totalCount =
            getCurrentState().selectedCategory?.count ?: getCurrentState().totalQuestions
        val adjustedCount = countNumber.coerceAtMost(maximumValue = totalCount)
        updateState { state ->
            state.copy(
                count = adjustedCount
            )
        }
    }
}
