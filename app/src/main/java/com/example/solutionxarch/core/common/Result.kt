package com.example.solutionxarch.core.common


sealed interface Result<out D, out E: Exception> {
    data class Success<out D, out E: Exception>(val data: D): Result<D, E>
    data class Failure<out D, out E: Exception>(val error: E): Result<D, E>
}