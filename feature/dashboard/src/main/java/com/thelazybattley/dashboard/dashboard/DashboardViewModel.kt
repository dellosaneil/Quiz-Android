package com.thelazybattley.dashboard.dashboard

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
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
    private val insertAllQuestionsUseCase: InsertAllQuestionsUseCase
) : BaseViewModel<DashboardEvents, DashboardUiState>() {

    override fun initialState() = DashboardUiState()

    init {
        viewModelScope.launch(context = dispatcher) {
            val isNotEmpty = getCategoriesDetails()
            if (isNotEmpty) {
                return@launch
            }
            fetchAllQuestionsUseCase().fold(
                onSuccess = { questions ->
                    insertAllQuestionsUseCase(questions = questions)
                    getCategoriesDetails()
                },
                onFailure = {

                }
            )
        }
    }

    private suspend fun getCategoriesDetails(): Boolean {
        getCategoryDetailsUseCase().fold(
            onSuccess = { categories ->
                updateState { state ->
                    state.copy(
                        categoriesDetails = categories.take(n = 4)
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
