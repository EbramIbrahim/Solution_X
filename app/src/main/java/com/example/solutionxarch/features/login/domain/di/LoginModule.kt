package com.example.solutionxarch.features.login.domain.di

import com.example.solutionxarch.core.domain.repository.local.IStorageKeyValue
import com.example.solutionxarch.core.domain.repository.remote.IRemoteDataSourceProvider
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.features.login.data.repository.remote.LoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.usecase.GetUserEntityUC
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import com.example.solutionxarch.features.login.domain.usecase.SaveTokenUseCase
import com.example.solutionxarch.features.login.domain.usecase.SaveUserEntityUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideLoginRemoteApi(provider: IRemoteDataSourceProvider): ILoginRemoteDataSource {
        return LoginRemoteDataSource(provider)
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
        provider: IStorageKeyValue
    ): ILoginLocalDataSource {
        return LoginLocalDataSource(provider)
    }

    @Provides
    @Singleton
    fun provideSaveUserEntityUC(
        repositoryImpl: LoginRepositoryImpl
    ): SaveUserEntityUC {
        return SaveUserEntityUC(repositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetUserEntityUC(
        repositoryImpl: LoginRepositoryImpl
    ): GetUserEntityUC {
        return GetUserEntityUC(repositoryImpl)
    }


}






