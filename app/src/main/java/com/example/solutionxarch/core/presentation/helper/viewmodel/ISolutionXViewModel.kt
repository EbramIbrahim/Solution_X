package com.example.solutionxarch.core.presentation.helper.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ISolutionXViewModel<Action: ViewAction, Event: ViewEvent, State: ViewState> {


    val singleEvent: Flow<Event>

    val state: StateFlow<State>


    fun processIntent(action: Action)


}