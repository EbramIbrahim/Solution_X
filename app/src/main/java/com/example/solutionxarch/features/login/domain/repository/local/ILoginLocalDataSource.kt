package com.example.solutionxarch.features.login.domain.repository.local

import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.domain.models.User

interface ILoginLocalDataSource {

    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): UserEntity


}