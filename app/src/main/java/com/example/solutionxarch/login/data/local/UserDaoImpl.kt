package com.example.solutionxarch.login.data.local

import com.example.solutionxarch.login.data.models.UserEntity
import com.example.solutionxarch.login.domain.contracts.LoginLocalDataSource

interface UserDaoImpl: LoginLocalDataSource {
    override fun getUser(): UserEntity

    override fun saveUser(userEntity: UserEntity)
}