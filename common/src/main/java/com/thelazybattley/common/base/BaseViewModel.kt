package com.thelazybattley.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : BaseEvents, ViewState : BaseUiState> : ViewModel() {

    private val initialState: ViewState by lazy {
        initialState()
    }

    abstract fun initialState(): ViewState

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(value = initialState)

    val state: StateFlow<ViewState> = _state

    fun emitEvent(event: Event) {
        viewModelScope.launch {
            _events.send(element = event)
        }
    }

    fun updateState(newState: (ViewState) -> ViewState) {
        _state.update(newState)
    }

    fun getCurrentState() = _state.value

    override fun onCleared() {
        super.onCleared()
        _events.close()
    }

}
