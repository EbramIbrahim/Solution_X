package com.example.solutionxarch.login.common

import kotlin.Exception

sealed class SolutionXException() :
    Exception() {
        data object RequestTimeOut: SolutionXException()
        data object TooManyRequest: SolutionXException()
        data object NoInternet: SolutionXException()
        data object ServerError: SolutionXException()
}