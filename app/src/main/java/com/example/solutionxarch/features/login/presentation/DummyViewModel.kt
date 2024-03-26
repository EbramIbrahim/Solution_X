package com.example.solutionxarch.features.login.presentation

import androidx.lifecycle.viewModelScope
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.core.presentation.SolutionXViewModel
import com.example.solutionxarch.core.presentation.ViewAction
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import javax.inject.Inject
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainAction
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainEvent
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DummyViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC
) : SolutionXViewModel<MainAction, MainEvent, MainState>(MainState.initial()) {

    override fun clearState() {
        TODO("Not yet implemented")
    }

    override fun onActionTrigger(action: MainAction?) {
        when(action) {
            MainAction.GetCountries -> TODO()
            is MainAction.LoginWithPhone -> {
                sendEvent(MainEvent.LoginIsSuccessfully("Successfully Login"))
            }
            null -> TODO()
        }
    }

    override val state: StateFlow<MainState>
        get() = super.state

    override val singleEvent: Flow<MainEvent>
        get() = super.singleEvent

    override fun processIntent(action: MainAction) {
        when (action) {
            MainAction.GetCountries -> TODO()
            is MainAction.LoginWithPhone -> {
                loginUserWithPhone(action.countryCode, action.phone, action.password)
            }
        }
    }


    private fun loginUserWithPhone(
        countryCode: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launch {
            loginWithPhoneUC(countryCode, phoneNumber, password).collectLatest { result ->
                when (result) {
                    is Result.Failure -> {
                        setState(MainState(false, result.error, null))
                    }

                    is Result.Success -> {
                        setState(
                            MainState(
                                false, null, MainAction.LoginWithPhone(
                                    phone = phoneNumber,
                                    password = password,
                                    countryCode = countryCode
                                )
                            )
                        )

                    }
                }
            }
        }
    }

}