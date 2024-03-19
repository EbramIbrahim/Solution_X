package com.example.solutionxarch.login.data.remote

import com.example.solutionxarch.login.data.models.UserDto
import com.example.solutionxarch.login.domain.contracts.LoginRemoteDataSource

interface Api: LoginRemoteDataSource {

    override fun loginUserWithPhone(): UserDto

    override fun loginUserWithEmail(): UserDto

    override fun loginUserWithSocial(): UserDto
}