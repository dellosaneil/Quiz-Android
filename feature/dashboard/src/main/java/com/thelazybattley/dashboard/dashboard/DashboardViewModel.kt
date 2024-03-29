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

    init {
        getAllQuizCategories()
        getAllQuizResults()
    }

    private fun getAllQuizCategories() {
        val quizType = QuizType.LIFE_OF_RIZAL
        viewModelScope.launch(context = dispatcher) {
            fetchAllQuestionsUseCase(
                quizType = quizType
            ).fold(
                onSuccess = { questions ->
                    insertAllQuestionsUseCase(questions = questions)
                    getCategoriesDetails(
                        quizType = quizType
                    )
                },
                onFailure = {
                    getCategoriesDetails(
                        quizType = quizType
                    )
                }
            )
        }
    }

    private fun getAllQuizResults() {
        viewModelScope.launch(context = dispatcher) {
            getAllQuizResultsUseCase()
                .fold(
                    onSuccess = { flow ->
                        flow.collect { quizResults ->
                            updateState { state ->
                                state.copy(
                                    quizResults = quizResults
                                )
                            }
                        }
                    },
                    onFailure = {

                    }
                )
        }
    }

    private suspend fun getCategoriesDetails(
        quizType: QuizType
    ): Boolean {
        getCategoryDetailsUseCase(quizType = quizType)
            .fold(
                onSuccess = { categories ->
                    updateState { state ->
                        when (quizType) {
                            QuizType.NOLI_ME_TANGERE -> {
                                state.copy(
                                    noliCategories = categories.take(n = 3)
                                )
                            }

                            QuizType.EL_FILI -> {
                                state.copy(
                                    elFiliCategories = categories.take(n = 3)
                                )
                            }

                            QuizType.LIFE_OF_RIZAL -> {
                                state.copy(
                                    lifeOfRizalCategories = categories.take(n = 3)
                                )
                            }
                        }
                    }
                    isFinishedLoading()
                },
                onFailure = { _ ->
                    // do nothing
                }
            )
        return when (quizType) {
            QuizType.NOLI_ME_TANGERE -> getCurrentState().noliCategories.isNotEmpty()
            QuizType.EL_FILI -> getCurrentState().elFiliCategories.isNotEmpty()
            QuizType.LIFE_OF_RIZAL -> getCurrentState().lifeOfRizalCategories.isNotEmpty()
        }
    }

    private fun isFinishedLoading() = run {
        updateState { state ->
            with(getCurrentState()) {
                state.copy(
                    isLoading = lifeOfRizalCategories.isEmpty()
                )
            }
        }
    }
}
