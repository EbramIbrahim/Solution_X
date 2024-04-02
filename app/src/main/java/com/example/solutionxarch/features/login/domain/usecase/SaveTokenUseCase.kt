package com.example.solutionxarch.features.login.domain.usecase

import android.util.Log
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val repositoryImpl: LoginRepositoryImpl
) {

    suspend operator fun invoke(token: String) {
        repositoryImpl.saveToken(token)
        Log.e("TAG", "token:$token was saved successfully")
    }
}