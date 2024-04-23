package com.example.solutionxarch.features.save_list.presentation

sealed interface ListValuesEvent {


    data class SaveListValues(val names: List<String>): ListValuesEvent
    data class UpdateListValuesWorkManager(val names: List<String>): ListValuesEvent
    data object GetListValues: ListValuesEvent




}