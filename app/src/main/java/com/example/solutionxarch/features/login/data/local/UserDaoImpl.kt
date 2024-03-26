package com.example.solutionxarch.features.login.data.local

import com.example.solutionxarch.features.login.data.models.UserEntity
import com.example.solutionxarch.features.login.domain.contracts.ILoginLocalDataSource

interface UserDaoImpl: ILoginLocalDataSource {
    override fun getUser(): UserEntity

    override fun saveUser(userEntity: UserEntity)
}