package com.example.solutionxarch.features.save_list.domain.usecase

import com.example.solutionxarch.features.save_list.domain.repository.repo.IListValuesRepository
import javax.inject.Inject

class GetListValuesUC @Inject constructor(
    private val repository: IListValuesRepository
) {

    suspend operator fun invoke(): String {
        return repository.getListValues()
    }
}