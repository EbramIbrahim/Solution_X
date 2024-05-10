package com.example.solutionxarch.core.presentation.helper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class SolutionXViewModel<Action: ViewAction, Event: ViewEvent, State: ViewState>(
    initialState: State
): ISolutionXViewModel<Action, Event, State>, ViewModel() {


    // StateFlow for current state
    private val _viewState: MutableStateFlow<State> = MutableStateFlow(initialState)
    override val state: StateFlow<State>
        get() = _viewState.asStateFlow()

    // Channel for UI EVENTS
    private val _singleEventChannel: Channel<Event> = Channel(Channel.UNLIMITED)
    override val singleEvent: Flow<Event>
        get() = _singleEventChannel.receiveAsFlow()

    // SharedFlow for actions result due to Event
    private val _viewAction = MutableSharedFlow<Action>(extraBufferCapacity = Int.MAX_VALUE)

    override fun processIntent(action: Action) {
        check(_viewAction.tryEmit(action)) { "Failed to emit action $action" }
    }


    protected fun sendEvent(event: Event) {
        _singleEventChannel.trySend(event)
    }

    fun setState(newState: State) {
        _viewState.value = newState
    }


    abstract fun clearState()

    private val actionSharedFlow: SharedFlow<Action>
        get() = _viewAction

    abstract fun onActionTrigger(action: Action?)

    init {
       viewModelScope.launch {
           actionSharedFlow.collect {
               onActionTrigger(it)
           }
       }
    }

    override fun onCleared() {
        super.onCleared()
        _singleEventChannel.close()
    }


}






