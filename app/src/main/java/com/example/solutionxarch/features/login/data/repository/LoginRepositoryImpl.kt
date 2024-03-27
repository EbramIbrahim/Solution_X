package com.example.solutionxarch.features.login.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.solutionxarch.features.login.data.local.LoginLocalDataSource
import com.example.solutionxarch.features.login.data.mapper.LoginMapper
import com.example.solutionxarch.core.domain.contracts.IRemoteDataSource
import com.example.solutionxarch.features.login.data.models.UserLoginData
import com.example.solutionxarch.features.login.data.models.UserLoginDto
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: IRemoteDataSource,
    private val dataStore: DataStore<Preferences>,
) : LoginRepository {

    override suspend fun loginUserWithPhone(
        userLoginData: UserLoginData
    ): User {
        val user =
            api.loginUserWithPhone(userLoginData.countryCode, userLoginData.number, userLoginData.password)
        return LoginMapper.toDomain(user)
    }

//    override suspend fun loginUserWithEmail(): User {
//        val user = api.loginUserWithEmail()
//        return LoginMapper.toDomain(user)
//    }
//
//    override suspend fun loginUserWithSocial(): User {
//        val user = api.loginUserWithSocial()
//        return LoginMapper.toDomain(user)
//    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey("")] = token
        }
    }


//
//    override suspend fun saveUser(user: User) {
//        loginLocalDataSource.save("", user)
//    }
}