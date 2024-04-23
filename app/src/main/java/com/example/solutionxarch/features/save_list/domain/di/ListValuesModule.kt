package com.example.solutionxarch.features.save_list.domain.di

import com.example.solutionxarch.core.data.repository.local.DataStoreStorageKeyValue
import com.example.solutionxarch.core.domain.repository.local.IStorageKeyValue
import com.example.solutionxarch.features.save_list.data.repository.local.LocalListValuesDataSource
import com.example.solutionxarch.features.save_list.data.repository.repo.ListValuesRepositoryImpl
import com.example.solutionxarch.features.save_list.domain.repository.local.ILocalListValuesDataSource
import com.example.solutionxarch.features.save_list.domain.repository.repo.IListValuesRepository
import com.example.solutionxarch.features.save_list.domain.usecase.GetListValuesUC
import com.example.solutionxarch.features.save_list.domain.usecase.SaveListValuesUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListValuesModule {


    @Provides
    @Singleton
    fun provideLocalListValuesDataSource(
        provider: IStorageKeyValue
    ): ILocalListValuesDataSource {
        return LocalListValuesDataSource(provider)
    }

    @Provides
    @Singleton
    fun provideListValuesRepository(
        localListValuesDataSource: ILocalListValuesDataSource
    ): IListValuesRepository {
        return ListValuesRepositoryImpl(localListValuesDataSource)
    }


    @Provides
    @Singleton
    fun provideSaveListValuesUC(
        repositoryImpl: ListValuesRepositoryImpl
    ): SaveListValuesUC {
        return SaveListValuesUC(repositoryImpl)
    }


    @Provides
    @Singleton
    fun provideGetListValuesUC(
        repositoryImpl: ListValuesRepositoryImpl
    ): GetListValuesUC {
        return GetListValuesUC(repositoryImpl)
    }

}













