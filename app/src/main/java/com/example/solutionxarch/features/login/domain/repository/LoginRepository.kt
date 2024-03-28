package com.example.solutionxarch.features.login.domain.repository

import com.example.solutionxarch.core.data.models.UserLoginData
import com.example.solutionxarch.features.login.domain.models.User

interface LoginRepository {
    suspend fun loginUserWithPhone(
        userLoginData: UserLoginData
    ): User
//    suspend fun loginUserWithEmail(): User
//    suspend fun loginUserWithSocial(): User


    suspend fun saveToken(token: String)

    suspend fun saveUser(user: User)

    suspend fun getUserToken(): String?

    suspend fun getUser(): String?

//    suspend fun saveUser(user: User)



}









