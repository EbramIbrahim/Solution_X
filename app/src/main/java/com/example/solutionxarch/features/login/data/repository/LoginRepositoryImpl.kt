package com.example.solutionxarch.features.login.data.repository

import com.example.solutionxarch.features.login.data.mapper.LoginMapper
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: ILoginRemoteDataSource,
    private val localDB: ILoginLocalDataSource
) : LoginRepository {

    override suspend fun loginUserWithPhone(
        userLoginData: Map<String, String>
    ): User {
        val user =
            api.loginUserWithPhone(userLoginData)
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