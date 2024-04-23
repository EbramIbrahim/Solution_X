package com.example.solutionxarch.features.save_list.domain.repository.repo

interface IListValuesRepository {

    suspend fun saveValueList(names: List<String>)
    suspend fun getListValues(): String
}