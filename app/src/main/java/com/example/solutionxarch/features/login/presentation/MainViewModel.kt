package com.example.solutionxarch.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainAction
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainEvent
import com.example.solutionxarch.features.login.presentation.MainViewContract.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC
) : ViewModel() {


    private val _loginState: MutableStateFlow<MainState> = MutableStateFlow(MainState.initial())
    val loginState
        get() =
            _loginState.asStateFlow()


    private val _eventChannel = Channel<MainEvent> { Channel.UNLIMITED }
    val eventChannel = _eventChannel.receiveAsFlow()

    private val _actionFlow = MutableSharedFlow<MainAction>(replay = Int.MAX_VALUE)

    init {

    }

    fun processIntent(action: MainAction) {
        when (action) {
            MainAction.GetCountries -> TODO()
            is MainAction.LoginWithPhone -> loginUserWithPhone(
                action.countryCode,
                action.phone,
                action.password
            )
        }
    }

    fun onActionTrigger(action: MainAction?) {
        when(action) {
            MainAction.GetCountries -> TODO()
            is MainAction.LoginWithPhone -> {}
            null -> TODO()
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
                        _loginState.update { it.copy(exception = result.error) }
                    }

                    is Result.Success -> {
                        _loginState.update {
                            it.copy(
                                action = MainAction.LoginWithPhone(
                                    phone = phoneNumber,
                                    password = password,
                                    countryCode = countryCode
                                )
                            )
                        }
                    }

                    is Result.Loading -> TODO()
                }
            }
        }
    }


}













