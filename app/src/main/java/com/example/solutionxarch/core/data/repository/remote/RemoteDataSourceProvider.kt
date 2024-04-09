package com.example.solutionxarch.core.data.repository.remote

import com.example.solutionxarch.core.domain.repository.remote.IRemoteDataSourceProvider
import com.google.gson.Gson
import java.lang.reflect.Type

// dealing like repository pattern
class RemoteDataSourceProvider(
    private val apiService: ApiService // <-- Can be replaced with another network call library
) : IRemoteDataSourceProvider {

    override suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type,
        endpoint: String,
        queries: Map<String, Any>?,
        headers: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody {
        val response = apiService.post(
            endPoint = endpoint,
            headers = headers ?: hashMapOf(),
            queries = queries ?: hashMapOf(),
            bodyRequest = requestBody ?: Unit
        )
        return Gson().fromJson(response.string(), responseWrappedModel)
    }

    override suspend fun <ResponseBody> get(
        responseWrappedModel: Type,
        endpoint: String,
        queries: Map<String, Any>?,
        headers: Map<String, Any>?
    ): ResponseBody {
        val response = apiService.get(
            endPoint = endpoint,
            headers = headers ?: hashMapOf(),
            queries = queries ?: hashMapOf()

        )
        return Gson().fromJson(response.string(), responseWrappedModel)
    }
}