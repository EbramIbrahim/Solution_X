package com.example.solutionxarch.login.data.repository

import com.example.solutionxarch.login.domain.contracts.LoginLocalDataSource
import com.example.solutionxarch.login.data.mapper.LoginMapper
import com.example.solutionxarch.login.domain.contracts.LoginRemoteDataSource
import com.example.solutionxarch.login.domain.repository.LoginRepository
import com.example.solutionxarch.login.domain.models.User

class LoginRepositoryImpl(
    private val api: LoginRemoteDataSource,
    private val localDatabase: LoginLocalDataSource,
): LoginRepository {

    override fun loginUserWithPhone(): User {
        val user = api.loginUserWithPhone()
        return LoginMapper.toDomain(user)
    }

    override fun loginUserWithEmail(): User {
        val user = api.loginUserWithEmail()
        return LoginMapper.toDomain(user)
    }

    override fun loginUserWithSocial(): User {
        val user = api.loginUserWithSocial()
        return LoginMapper.toDomain(user)
    }

    override fun getUserFromLocal(): User {
        val user = localDatabase.getUser()
        return LoginMapper.fromEntityToDomain(user)
    }

    override fun saveUser(user: User) {
        val userEntity = LoginMapper.toEntity(user)
        localDatabase.saveUser(userEntity)
    }
}