package com.example.solutionxarch.features.login.data.repository

import com.example.solutionxarch.features.login.data.mapper.LoginMapper
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.data.repository.local.CryptoLoginLocalDataSource
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: ILoginRemoteDataSource,
    private val crypto: CryptoLoginLocalDataSource
) : LoginRepository {

    override suspend fun loginUserWithPhone(
        userRequest: UserRequest
    ): User {
        val user =
            api.loginUserWithPhone(userRequest)
        return LoginMapper.toDomain(user)
    }


    override suspend fun saveUser(user: User) {
        crypto.saveUserEntity(LoginMapper.toEntity(user))
    }

    override suspend fun getUser(): UserEntity {
        return crypto.getUserEntity()
    }


}