package com.example.solutionxarch.features.login.data.repository

import com.example.solutionxarch.features.login.domain.contracts.ILoginLocalDataSource
import com.example.solutionxarch.features.login.data.mapper.LoginMapper
import com.example.solutionxarch.features.login.data.models.meal_dto.MealsDto
import com.example.solutionxarch.features.login.domain.contracts.ILoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: ILoginRemoteDataSource,
    private val localDatabase: ILoginLocalDataSource
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

    override suspend fun getUserFromLocal(): User {
        val user = localDatabase.getUser()
        return LoginMapper.fromEntityToDomain(user)
    }

    override suspend fun saveUser(user: User) {
        val userEntity = LoginMapper.toEntity(user)
        localDatabase.saveUser(userEntity)
    }


}