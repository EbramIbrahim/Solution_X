package com.example.solutionxarch.features.login.presentation

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.core.presentation.ViewAction
import com.example.solutionxarch.core.presentation.ViewEvent
import com.example.solutionxarch.core.presentation.ViewState

interface MainViewContract {
    sealed class MainAction : ViewAction {
        data object GetCountries : MainAction()
        data class LoginWithEmail(val email: String, val password: String) : MainAction()
    }

    sealed class MainEvent : ViewEvent {
        data class ValidationError(val errors: Map<String, String>) : MainEvent()
//        data class CountriesIndex(val countries: List<Country>) : MainEvent()
        data class LoginIsSuccessfully(val message: String) : MainEvent()
        // Navigate to Signup
    }

    data class MainState(
        val isLoading: Boolean, val exception: SolutionXException?, val action: ViewAction?
    ) : ViewState {
        companion object {
            fun initial() = MainState(
                isLoading = false, exception = null, action = null
            )
        }
    }
}