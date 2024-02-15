package com.thelazybattley.dashboard.dashboard

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.domain.network.usecase.FetchCategoryDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fetchCategoryDetails: FetchCategoryDetails
) : BaseViewModel<DashboardEvents, DashboardUiState>() {

    override fun initialState() = DashboardUiState()

    init {
        viewModelScope.launch(context = dispatcher) {
            fetchCategoryDetails().fold(
                onSuccess = { categories ->
                    updateState { state ->
                        state.copy(
                            categoriesDetails = categories.take(n = 4)
                        )
                    }
                },
                onFailure = { throwable ->
                    updateState { state ->
                        state.copy(
                            throwable = throwable
                        )
                    }
                }
            )
        }
    }

}
