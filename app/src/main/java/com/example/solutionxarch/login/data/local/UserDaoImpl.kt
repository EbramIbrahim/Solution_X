package com.example.solutionxarch.login.data.local

import com.example.solutionxarch.login.data.models.UserEntity

interface UserDaoImpl: UserDao {
    override fun getUser(): UserEntity

    override fun saveUser(userEntity: UserEntity)
}