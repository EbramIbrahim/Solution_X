package com.example.solutionxarch.features.login.domain.contracts

import com.example.solutionxarch.features.login.data.models.UserEntity

interface ILoginLocalDataSource {
    fun getUser(): UserEntity
    fun saveUser(userEntity: UserEntity)
}