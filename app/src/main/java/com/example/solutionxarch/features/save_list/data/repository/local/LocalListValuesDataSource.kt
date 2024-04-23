package com.example.solutionxarch.features.save_list.data.repository.local

import com.example.solutionxarch.core.data.repository.local.keys.StorageKeys
import com.example.solutionxarch.core.domain.repository.local.IStorageKeyValue
import com.example.solutionxarch.features.save_list.domain.repository.local.ILocalListValuesDataSource
import javax.inject.Inject

class LocalListValuesDataSource @Inject constructor(
    private val provider: IStorageKeyValue
): ILocalListValuesDataSource {

    override suspend fun saveListValues(names: List<String>) {
        val namesToString = names.joinToString()
        provider.save(StorageKeys.LIST_VALUES, namesToString)
    }

    override suspend fun getListValues(): String {
        return provider.read(StorageKeys.LIST_VALUES, "")
    }
}