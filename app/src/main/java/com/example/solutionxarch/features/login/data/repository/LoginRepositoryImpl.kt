package com.example.solutionxarch.features.login.data.repository

import com.example.solutionxarch.features.login.data.local.LoginLocalDataSource
import com.example.solutionxarch.features.login.data.mapper.LoginMapper
import com.example.solutionxarch.core.domain.contracts.IRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: IRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
): LoginRepository {

    override suspend fun loginUserWithPhone(
        countryCode: String,
        phoneNumber: String,
        password: String
    ): User {
        val user = api.loginUserWithPhone(countryCode, phoneNumber, password)
        return LoginMapper.toDomain(user)
    }

    override suspend fun loginUserWithEmail(): User {
        val user = api.loginUserWithEmail()
        return LoginMapper.toDomain(user)
    }

    override suspend fun loginUserWithSocial(): User {
        val user = api.loginUserWithSocial()
        return LoginMapper.toDomain(user)
    }

    override suspend fun saveToken(token: String) {
        loginLocalDataSource.save("", token)
    }

    override suspend fun saveUser(user: User) {
        loginLocalDataSource.save("", user)
    }
}