package com.thelazybattley.quiz.quizresultshistory

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.domain.local.GetAllQuizResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizResultsHistoryViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getAllQuizResultsUseCase: GetAllQuizResultsUseCase
) : BaseViewModel<QuizResultsHistoryEvents, QuizResultsHistoryUiState>(),
    QuizResultsHistoryCallbacks {

    override fun initialState() = QuizResultsHistoryUiState()

    init {
        getAllQuizResults()

    }

    private fun getAllQuizResults() {
        viewModelScope
            .launch(context = dispatcher) {
                getAllQuizResultsUseCase()
                    .collect{result ->
                        result.fold(
                            onSuccess = { results ->
                                updateState { state ->
                                    state.copy(
                                        filteredQuizResult = results,
                                        categories = results.map { it.category }.distinct(),
                                        completeQuizResult = results
                                    )
                                }
                            },
                            onFailure = {

                            }
                        )
                    }

            }
    }

    override fun onSelectCategory(category: String) {
        updateState { state ->
            val updatedCategory = if (category == state.selectedCategory) null else category
            val updatedFilteredQuizResults =
                if (updatedCategory == null) state.completeQuizResult else state.completeQuizResult.filter { it.category == category }
            state.copy(
                selectedCategory = updatedCategory,
                filteredQuizResult = updatedFilteredQuizResults
            )
        }
    }
}
