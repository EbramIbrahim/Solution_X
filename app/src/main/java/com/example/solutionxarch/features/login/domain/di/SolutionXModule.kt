package com.example.solutionxarch.features.login.domain.di

import com.example.solutionxarch.core.common.Utils.BASE_URL
import com.example.solutionxarch.features.login.data.remote.LoginRemoteDataSource
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.features.login.domain.contracts.ILoginRemoteDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SolutionXModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): ILoginRemoteDataSource {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginRemoteDataSource::class.java)
    }


    @Provides
    @Singleton
    fun provideRepositoryWithRetrofit(
        loginRemoteDataSource: LoginRemoteDataSource
    ): LoginRepository{
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideDummyUseCase(
        repositoryImpl: LoginRepositoryImpl
    ): LoginWithPhoneUC {
        return LoginWithPhoneUC(repositoryImpl)
    }


}






