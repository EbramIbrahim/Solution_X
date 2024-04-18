package com.example.solutionxarch.di


import com.example.solutionxarch.core.domain.di.NetWorkModule
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.features.login.domain.di.LoginModule
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LoginModule::class, NetWorkModule::class]
)
object RepositoryTestModule {

        @Provides
        @Singleton
        @Named("repository_test")
        fun provideRepositoryWithRetrofit(
            loginRemoteDataSource: ILoginRemoteDataSource,
            loginLocalDataSource: ILoginLocalDataSource
        ): LoginRepository {
            return LoginRepositoryImpl(loginRemoteDataSource, loginLocalDataSource)
        }
    }