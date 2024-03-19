package com.example.solutionxarch.core.common

import kotlin.Exception

sealed class SolutionXException(message: String):  Exception(){
    data class RequestTimeOut(val requestTimeOutMessage: String) :
        SolutionXException(requestTimeOutMessage)
    data class TooManyRequest(val tooManyRequestMessage: String) :
        SolutionXException(tooManyRequestMessage)
    data class NoInternet(val noInternetMessage: String) :
        SolutionXException(noInternetMessage)
    data class ServerError(val serverErrorMessage: String) :
        SolutionXException(serverErrorMessage)


}