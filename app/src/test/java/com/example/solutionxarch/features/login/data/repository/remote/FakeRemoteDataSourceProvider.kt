package com.example.solutionxarch.features.login.data.repository.remote

import com.example.solutionxarch.core.data.repository.remote.ApiService
import com.example.solutionxarch.core.domain.repository.remote.IRemoteDataSourceProvider
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

class FakeRemoteDataSourceProvider(
    private val apiService: ApiService
): IRemoteDataSourceProvider {

    override suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type,
        endpoint: String,
        queries: Map<String, Any>?,
        headers: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody? {
        val response = apiService.post(
            endPoint = endpoint,
            headers = headers ?: hashMapOf(),
            queries = queries ?: hashMapOf(),
            bodyRequest = requestBody ?: Unit
        )
        try {
            return Gson().fromJson(response.string(), responseWrappedModel)
        } catch (e: JsonSyntaxException){}
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