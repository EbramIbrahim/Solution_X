package com.example.solutionxarch.core.common


sealed class Result<out D> {
    data class Success<out D>(val data: D): Result<D>()
    data class Failure(val error:SolutionXException): Result<Nothing>()

    data class Loading(val isLoading: Boolean = true): Result<Nothing>()
}