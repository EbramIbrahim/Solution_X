package com.example.solutionxarch.features.login.domain.repository

import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.domain.models.User

interface LoginRepository {
    suspend fun loginUserWithPhone(
        userRequest: UserRequest
    ): User


    suspend fun saveToken(token: String)

    suspend fun saveUser(user: User)

    suspend fun getUser(): UserEntity?




}









