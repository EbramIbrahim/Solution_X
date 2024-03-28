package com.example.solutionxarch.features.login.data.repository

import com.example.solutionxarch.core.data.models.UserLoginData
import com.example.solutionxarch.features.login.data.mapper.LoginMapper
import com.example.solutionxarch.features.login.domain.contracts.ILocalDataSource
import com.example.solutionxarch.features.login.domain.contracts.IRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: IRemoteDataSource,
    private val localDB: ILocalDataSource
) : LoginRepository {

    override suspend fun loginUserWithPhone(
        userLoginData: UserLoginData
    ): User {
        val user =
            api.loginUserWithPhone(
                mapOf(
                    "phone[country_code]" to userLoginData.countryCode,
                    "phone[number]" to userLoginData.phone,
                    "password" to userLoginData.password,
                )
            )
        return LoginMapper.toDomain(user)
    }

    override suspend fun saveToken(token: String) {
        localDB.saveToken(token)
    }

    override suspend fun saveUser(user: User) {
        localDB.saveUser(user)
    }

    override suspend fun getUserToken(): String? {
        return localDB.getToken()
    }

    override suspend fun getUser(): String? {
        return localDB.getUser()
    }
}