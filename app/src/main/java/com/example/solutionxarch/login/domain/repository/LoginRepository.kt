package com.example.solutionxarch.login.domain.repository

import com.example.solutionxarch.login.data.models.UserEntity
import com.example.solutionxarch.login.domain.models.User

interface LoginRepository {
    fun loginUserWithPhone(): User
    fun loginUserWithEmail(): User
    fun loginUserWithSocial(): User
    fun getUserFromLocal(): User

    fun saveUser(user: User)
}