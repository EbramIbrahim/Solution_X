package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.features.login.domain.models.User
import javax.inject.Inject

class GetUserEntityUC @Inject constructor(
    private val repository: LoginRepositoryImpl
) {

    suspend operator fun invoke(): UserEntity {
        return repository.getUser()
    }
}