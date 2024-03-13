package com.example.solutionxarch.login.data.local

import com.example.solutionxarch.login.data.models.UserEntity

interface UserDao {
    fun getUser(): UserEntity
    fun saveUser(userEntity: UserEntity)
}