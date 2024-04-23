package com.example.solutionxarch.features.save_list.data.repository.repo

import com.example.solutionxarch.features.save_list.domain.repository.local.ILocalListValuesDataSource
import com.example.solutionxarch.features.save_list.domain.repository.repo.IListValuesRepository
import javax.inject.Inject

class ListValuesRepositoryImpl @Inject constructor(
    private val localListValuesDataSource: ILocalListValuesDataSource
): IListValuesRepository {

    override suspend fun saveValueList(names: List<String>) {
        localListValuesDataSource.saveListValues(names)
    }

    override suspend fun getListValues(): String {
        return localListValuesDataSource.getListValues()
    }
}