package com.example.solutionxarch.core.domain.di

import android.content.Context
import com.example.solutionxarch.core.common.Utils
import com.example.solutionxarch.core.data.repository.local.CryptoManager
import com.example.solutionxarch.core.data.repository.local.DataStoreStorageKeyValue
import com.example.solutionxarch.core.data.repository.local.EntitySerializer
import com.example.solutionxarch.core.data.repository.local.SecureDataStoreStorageKV
import com.example.solutionxarch.core.data.repository.remote.ApiService
import com.example.solutionxarch.core.data.repository.remote.RemoteDataSourceProvider
import com.example.solutionxarch.core.domain.repository.local.ISecureStorageKeyValue
import com.example.solutionxarch.core.domain.repository.local.IStorageKeyValue
import com.example.solutionxarch.core.domain.repository.remote.IRemoteDataSourceProvider
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginRemoteApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideLocalStorageProvider(
        @ApplicationContext context: Context
    ): IStorageKeyValue {
        return DataStoreStorageKeyValue(context)
    }

    @Singleton
    @Provides
    fun provideSecureLocalStorage(
        @ApplicationContext context: Context
    ): ISecureStorageKeyValue<UserEntity> {
        return SecureDataStoreStorageKV(
            context,
            EntitySerializer(CryptoManager(), UserEntity.serializer()) { UserEntity() })
    }


    @Provides
    @Singleton
    fun provideRemoteDataSourceProvider(apiService: ApiService): IRemoteDataSourceProvider {
        return RemoteDataSourceProvider(apiService)
    }


}