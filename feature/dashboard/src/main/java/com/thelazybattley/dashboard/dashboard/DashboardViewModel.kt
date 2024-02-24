package com.thelazybattley.dashboard.dashboard

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.domain.local.GetAllQuizResultsUseCase
import com.thelazybattley.domain.local.InsertAllQuestionsUseCase
import com.thelazybattley.domain.network.usecase.FetchAllQuestionsUseCase
import com.thelazybattley.domain.network.usecase.GetCategoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getCategoryDetailsUseCase: GetCategoryDetailsUseCase,
    private val fetchAllQuestionsUseCase: FetchAllQuestionsUseCase,
    private val insertAllQuestionsUseCase: InsertAllQuestionsUseCase,
    private val getAllQuizResultsUseCase: GetAllQuizResultsUseCase
) : BaseViewModel<DashboardEvents, DashboardUiState>(), DashboardCallbacks {

    override fun initialState() = DashboardUiState()

    override fun initialize() {
        viewModelScope.launch(context = dispatcher) {
            val isNotEmpty = getCategoriesDetails()
            if (isNotEmpty) {
                return@launch
            }
            fetchAllQuestionsUseCase(
                quizType = QuizType.LIFE_OF_RIZAL
            ).fold(
                onSuccess = { questions ->
                    insertAllQuestionsUseCase(questions = questions)
                    getCategoriesDetails()
                },
                onFailure = {

                }
            )
        }
        viewModelScope.launch(context = dispatcher) {
            getAllQuizResults()
        }
    }

    private suspend fun getAllQuizResults() {
        getAllQuizResultsUseCase()
            .fold(
                onSuccess = { results ->
                    updateState { state ->
                        state.copy(
                            quizResults = results.take(3),
                            quizResultExceeding = results.size - 3
                        )
                    }
                },
                onFailure = {

                }
            )
    }

    private suspend fun getCategoriesDetails(): Boolean {
        getCategoryDetailsUseCase(quizType = QuizType.LIFE_OF_RIZAL).fold(
            onSuccess = { categories ->
                updateState { state ->
                    state.copy(
                        categoriesDetails = categories.take(n = 3)
                    )
                }
            },
            onFailure = { _ ->
                // do nothing
            }
        )
        return getCurrentState().categoriesDetails.isNotEmpty()
    }

}
