package com.example.solutionxarch.login.domain

import com.example.solutionxarch.login.domain.models.User

interface LoginRepository {
    fun loginUserWithPhone(): User
    fun loginUserWithEmail(): User
    fun loginUserWithSocial(): User
    fun getUserFromLocal(): User
}