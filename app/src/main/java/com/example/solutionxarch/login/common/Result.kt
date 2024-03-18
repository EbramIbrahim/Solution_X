package com.example.solutionxarch.login.common

typealias ErrorRoot = Exception

sealed interface Result<out D, out E: Exception> {
    data class Success<out D, out E: Exception>(val data: D): Result<D, E>
    data class Error<out D, out E: Exception>(val error: E): Result<D, E>
}