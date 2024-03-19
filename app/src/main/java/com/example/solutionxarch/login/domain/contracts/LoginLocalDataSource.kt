package com.example.solutionxarch.login.domain.contracts

import com.example.solutionxarch.login.data.models.UserEntity

interface LoginLocalDataSource {
    fun getUser(): UserEntity
    fun saveUser(userEntity: UserEntity)
}