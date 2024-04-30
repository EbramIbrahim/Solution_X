package com.example.solutionxarch.features.save_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.solutionxarch.core.android.helpers.logger.writer.LogcatWriter
import com.example.solutionxarch.features.save_list.domain.usecase.GetListValuesUC
import com.example.solutionxarch.features.save_list.domain.usecase.SaveListValuesUC
import com.example.solutionxarch.features.save_list.domain.usecase.worker.UpdateListValuesWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListValuesViewModel @Inject constructor(
    private val saveListValuesUC: SaveListValuesUC,
    private val getListValuesUC: GetListValuesUC,
    private val workManager: WorkManager
): ViewModel() {

    fun onEvent(event: ListValuesEvent) {
        when(event) {
            is ListValuesEvent.SaveListValues -> {
                saveListValues(event.names)
            }

            ListValuesEvent.GetListValues -> {
                getListValues()
            }

            is ListValuesEvent.UpdateListValuesWorkManager -> {
                updateListValuesWithWorkManager(event.names)
            }
        }
    }

    private val logger = LogcatWriter("Tag", true)

    private fun saveListValues(names: List<String>) = viewModelScope.launch {
        saveListValuesUC(names)
    }

    private fun getListValues() = viewModelScope.launch {
        val names = getListValuesUC()
        Log.d("TAG", names)
    }


    private fun updateListValuesWithWorkManager(names: List<String>) = viewModelScope.launch {
        val workRequest = OneTimeWorkRequestBuilder<UpdateListValuesWorker>()
            .setInputData(
                Data.Builder()
                    .putStringArray(
                        UpdateListValuesWorker.ARABIC_LIST_VALUES_KEY, names.toTypedArray()
                    ).build()
            )
            .build()
        workManager.enqueueUniqueWork(
            "updateListValuesWorker",
            ExistingWorkPolicy.KEEP,
            workRequest
        )

        workManager.getWorkInfoByIdFlow(workRequest.id).collectLatest { workInfo ->
            workInfo?.let { result ->
                when(result.state) {
                    WorkInfo.State.ENQUEUED -> {
                        logger.debug(String::class.java, "Worker is ENQUEUED")
                    }
                    WorkInfo.State.RUNNING -> {
                        logger.debug(String::class.java, "Worker is RUNNING")
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        logger.debug(String::class.java, "Worker is SUCCEEDED")
                        logger.debug(String::class.java, "The List has been updated Successfully..")
                    }
                    WorkInfo.State.FAILED -> {
                        logger.debug(String::class.java, "Worker is FAILED")
                    }
                    WorkInfo.State.BLOCKED -> {
                        logger.debug(String::class.java, "Worker is BLOCKED")
                    }
                    WorkInfo.State.CANCELLED -> {
                        logger.debug(String::class.java, "Worker is CANCELLED")
                    }
                }
            }


        }
    }

}
















