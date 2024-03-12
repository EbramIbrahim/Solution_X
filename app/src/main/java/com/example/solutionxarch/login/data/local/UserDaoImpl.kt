package com.example.solutionxarch.login.data.local

interface UserDaoImpl: UserDao {
    override fun getUser(): UserEntity
}