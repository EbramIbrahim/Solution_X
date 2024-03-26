package com.example.solutionxarch.features.login.presentation

import com.example.solutionxarch.core.presentation.SolutionXViewModel
import com.example.solutionxarch.core.presentation.ViewAction
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainAction
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainEvent
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC
):  SolutionXViewModel<MainAction, MainEvent, MainState>(MainState.initial()) {


    override val state: StateFlow<MainState>
        get() = super.state
    override val singleEvent: Flow<MainEvent>
        get() = super.singleEvent

    override fun processIntent(action: MainAction) {
        super.processIntent(action)
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun clearState() {
        TODO("Not yet implemented")
    }

    override fun onActionTrigger(action: ViewAction?) {
        TODO("Not yet implemented")
    }
}


