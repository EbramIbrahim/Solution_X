package com.example.solutionxarch.features.login.data.repository.remote

import com.example.solutionxarch.core.common.Utils.BASE_URL
import com.example.solutionxarch.core.data.repository.remote.ApiService
import com.example.solutionxarch.features.login.data.models.request.PhoneRequest
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginRemoteDataSourceTest {

    private lateinit var apiService: ApiService
    private lateinit var fakeRemoteDataSource: FakeRemoteDataSourceProvider
    private lateinit var loginRemoteDataSource: LoginRemoteDataSource

    private val userRequest = UserRequest(
        PhoneRequest("0020", "100100100"), password = "123456789")

    private val emptyRequest = UserRequest(
        PhoneRequest("", ""), password = ""
    )
    @Before
    fun setUp() {
        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

        fakeRemoteDataSource = FakeRemoteDataSourceProvider(apiService)
        loginRemoteDataSource = LoginRemoteDataSource(fakeRemoteDataSource)
    }



    @Test
    fun retrofit_postUserRequest() = runTest {
        val response = loginRemoteDataSource.loginUserWithPhone(userRequest)
        assertEquals(true, response.userDto?.email?.isNotEmpty())
    }

    @Test()
    fun retrofit_postEmptyRequest() = runTest {
        val response = loginRemoteDataSource.loginUserWithPhone(emptyRequest)
        assertEquals(true, response.token.isNullOrEmpty())
    }

    @Test
    fun retrofit_postResponseStatusCode() = runTest {
        try {
            loginRemoteDataSource.loginUserWithPhone(emptyRequest)
        } catch (e: HttpException) {
            assertEquals(401, e.code())
        }

    }

}









