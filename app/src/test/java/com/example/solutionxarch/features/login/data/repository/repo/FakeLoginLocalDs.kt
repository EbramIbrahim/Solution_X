package com.example.solutionxarch.features.login.data.repository.repo

import com.example.solutionxarch.features.login.data.mapper.LoginMapper
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.domain.models.User
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource

class FakeLoginLocalDs : ILoginLocalDataSource {

    private val users: MutableList<User> = mutableListOf()

    override suspend fun saveUser(user: UserEntity) {
        users.add(LoginMapper.fromEntityToDomain(user))
    }

    override suspend fun getUser(): UserEntity {
        return users.first().let { LoginMapper.toEntity(it) }
    }
}