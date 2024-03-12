package com.example.solutionxarch.login.data.remote

import com.example.solutionxarch.login.data.models.UserDto

interface Api: RemoteDataConfig {

    override fun loginUserWithPhone(): UserDto

    override fun loginUserWithEmail(): UserDto

    override fun loginUserWithSocial(): UserDto
}