package com.example.solutionxarch.login.data.remote

import com.example.solutionxarch.login.data.models.UserDto

interface RemoteDataConfig {
    fun loginUserWithPhone(): UserDto
    fun loginUserWithEmail(): UserDto
    fun loginUserWithSocial(): UserDto
}