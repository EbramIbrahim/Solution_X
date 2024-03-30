package com.example.solutionxarch.features.login.domain.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.features.login.data.repository.remote.LoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import com.example.solutionxarch.features.login.domain.usecase.SaveTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import com.example.solutionxarch.features.login.data.repository.local.LoginLocalDataSource as LoginLocalDataSource

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideRepositoryWithRetrofit(
        loginRemoteDataSource: ILoginRemoteDataSource,
        loginLocalDataSource: ILoginLocalDataSource
        ): LoginRepository{
        return LoginRepositoryImpl(loginRemoteDataSource, loginLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRemoteApi(retrofit: Retrofit): ILoginRemoteDataSource {
        return retrofit.create(LoginRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginWithPhoneUseCase(
        repositoryImpl: LoginRepositoryImpl
    ): LoginWithPhoneUC {
        return LoginWithPhoneUC(repositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSaveTokenUseCase(
        repositoryImpl: LoginRepositoryImpl
    ): SaveTokenUseCase {
        return SaveTokenUseCase(repositoryImpl)
    }

    @Provides
    @Singleton
    fun provideLoginLocalDataSource(
        dataStore: DataStore<Preferences>
    ): ILoginLocalDataSource {
        return LoginLocalDataSource(dataStore)
    }


}






