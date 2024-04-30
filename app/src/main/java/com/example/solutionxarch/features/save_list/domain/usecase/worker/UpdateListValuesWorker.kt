package com.example.solutionxarch.features.save_list.domain.usecase.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.solutionxarch.features.save_list.domain.usecase.SaveListValuesUC
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class UpdateListValuesWorker @AssistedInject constructor(
    private val saveListValuesUC: SaveListValuesUC,
    @Assisted context: Context,
    @Assisted val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val names = workerParameters.inputData.getStringArray(ARABIC_LIST_VALUES_KEY)

        return try {
            names?.toList()?.let {
                saveListValuesUC(it)
                Log.d("UpdateListValuesWorker", "Success..!")
                Log.d("UpdateListValuesWorker", names.toString())
                Result.success(
                    Data.Builder().putString("Success", "The List has been updated Successfully..")
                        .build()
                )
            }
            Result.failure(Data.Builder().putString("error", "The list is Empty").build())

        } catch (e: Exception) {
            Log.d("UpdateListValuesWorker", "Error..!")
            Result.failure(Data.Builder().putString("error", e.message.toString()).build())
        }
    }

    companion object {
        const val ARABIC_LIST_VALUES_KEY = "ARABIC_LIST_VALUES_KEY"
    }
}






