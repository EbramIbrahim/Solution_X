package com.example.solutionxarch.login.domain.contracts

import com.example.solutionxarch.login.data.models.UserDto

interface LoginRemoteDataSource {
    fun loginUserWithPhone(): UserDto
    fun loginUserWithEmail(): UserDto
    fun loginUserWithSocial(): UserDto
}