package com.example.solutionxarch.features.login.domain.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.solutionxarch.core.common.Utils.BASE_URL
import com.example.solutionxarch.core.common.Utils.USER_PREFERENCES
import com.example.solutionxarch.features.login.data.remote.RemoteDataSource
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.core.domain.contracts.IRemoteDataSource
import com.example.solutionxarch.features.login.data.local.LoginLocalDataSource
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SolutionXModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): IRemoteDataSource {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryWithRetrofit(
        loginRemoteDataSource: RemoteDataSource,
        loginLocalDataSource: LoginLocalDataSource
    ): LoginRepository{
        return LoginRepositoryImpl(loginRemoteDataSource, loginLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginWithPhoneUseCase(
        repositoryImpl: LoginRepositoryImpl
    ): LoginWithPhoneUC {
        return LoginWithPhoneUC(repositoryImpl)
    }


    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext,USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }


}






