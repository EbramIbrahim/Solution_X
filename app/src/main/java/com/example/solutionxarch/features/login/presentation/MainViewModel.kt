package com.example.solutionxarch.features.login.presentation

import androidx.lifecycle.viewModelScope
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.core.presentation.SolutionXViewModel
import com.example.solutionxarch.features.login.data.models.UserLoginData
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainAction
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainEvent
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC
) : SolutionXViewModel<MainAction, MainEvent, MainState>(MainState.initial()) {


    override val state: StateFlow<MainState>
        get() = super.state
    override val singleEvent: Flow<MainEvent>
        get() = super.singleEvent



    override fun clearState() {
        TODO("Not yet implemented")
    }

    override fun onActionTrigger(action: MainAction?) {
        when(action) {
            MainAction.GetCountries -> TODO()
            is MainAction.LoginWithPhone -> {
                sendEvent(MainEvent.LoginIsSuccessfully("Login Successfully"))
            }
            null -> TODO()
        }
    }

    fun onProcessIntent(action: MainAction) {
        when (action) {
            MainAction.GetCountries -> TODO()
            is MainAction.LoginWithPhone -> loginUserWithPhone(
                UserLoginData(
                    countryCode = action.countryCode,
                    number = action.phone,
                    password = action.password
                )
            )
        }
    }

    private fun loginUserWithPhone(
        userLoginData: UserLoginData
    ) {
        loginWithPhoneUC(viewModelScope, userLoginData = userLoginData, onResult = { result ->
            when (result) {
                is Result.Failure -> TODO()
                is Result.Loading -> TODO()
                is Result.Success -> {
                    onProcessIntent(MainAction.LoginWithPhone(
                        userLoginData.countryCode,
                        userLoginData.number,
                        userLoginData.password
                    )
                    )
                }
            }
        })

    }


    override fun onCleared() {
        super.onCleared()
    }
}













