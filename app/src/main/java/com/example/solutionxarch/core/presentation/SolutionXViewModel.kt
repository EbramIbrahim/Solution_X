package com.example.solutionxarch.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class SolutionXViewModel<Action: ViewAction, Event: ViewEvent, State: ViewState>(
    initialState: State
): ISolutionXViewModel<Action, Event, State>, ViewModel() {


    private val _viewState: MutableStateFlow<State> = MutableStateFlow(initialState)
    override val state: StateFlow<State>
        get() = _viewState.asStateFlow()


    private val _eventChannel: Channel<Event> = Channel(Channel.UNLIMITED)
    override val singleEvent: Flow<Event>
        get() = _eventChannel.receiveAsFlow()

    private val _viewAction = MutableSharedFlow<Action>(extraBufferCapacity = Int.MAX_VALUE)

    override fun processIntent(action: Action) {
        check(_viewAction.tryEmit(action)) { "Failed to emit action $action" }
    }


    protected fun sendEvent(event: Event) {
        _eventChannel.trySend(event)
    }

    fun setState(newState: State) {
        _viewState.value = newState
    }


    abstract fun clearState()

    private val actionSharedFlow: SharedFlow<Action>
        get() = _viewAction

    abstract fun onActionTrigger(action: ViewAction?)

    init {
       viewModelScope.launch {
           actionSharedFlow.collect {
               onActionTrigger(it)
           }
       }
    }

    override fun onCleared() {
        super.onCleared()
        _eventChannel.close()
    }


}






