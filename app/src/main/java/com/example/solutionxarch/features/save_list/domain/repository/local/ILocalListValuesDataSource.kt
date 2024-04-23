package com.example.solutionxarch.features.save_list.domain.repository.local

interface ILocalListValuesDataSource {

    suspend fun saveListValues(names: List<String>)

    suspend fun getListValues(): String

}