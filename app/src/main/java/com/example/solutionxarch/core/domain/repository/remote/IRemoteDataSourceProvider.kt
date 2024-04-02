package com.example.solutionxarch.core.domain.repository.remote

import java.lang.reflect.Type

interface IRemoteDataSourceProvider {


    suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type,
        endpoint: String,
        queries: Map<String, Any>? = null,
        headers: Map<String, Any>? = null,
        requestBody: RequestBody? = null
    ): ResponseBody

    suspend fun <ResponseBody> get(
        responseWrappedModel: Type,
        endpoint: String,
        queries: Map<String, Any>? = null,
        headers: Map<String, Any>? = null,
    ): ResponseBody

}












