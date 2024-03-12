package com.example.solutionxarch.login.data.repository

import com.example.solutionxarch.login.data.local.UserDaoImpl
import com.example.solutionxarch.login.data.local.UserEntity
import com.example.solutionxarch.login.data.mapper.Mapper
import com.example.solutionxarch.login.data.models.UserDto
import com.example.solutionxarch.login.data.remote.Api
import com.example.solutionxarch.login.domain.repository.LoginRepository
import com.example.solutionxarch.login.domain.models.User

class LoginRepositoryImpl(
    private val api: Api,
    private val localDatabase: UserDaoImpl,
    private val mapper: Mapper<User, UserDto, UserEntity>
): LoginRepository {

    override fun loginUserWithPhone(): User {
        val user = api.loginUserWithPhone()
        return mapper.toDomain(user)
    }

    override fun loginUserWithEmail(): User {
        val user = api.loginUserWithEmail()
        return mapper.toDomain(user)
    }

    override fun loginUserWithSocial(): User {
        val user = api.loginUserWithSocial()
        return mapper.toDomain(user)
    }

    override fun getUserFromLocal(): User {
        val user = localDatabase.getUser()
        return mapper.fromEntityToDomain(user)
    }
}