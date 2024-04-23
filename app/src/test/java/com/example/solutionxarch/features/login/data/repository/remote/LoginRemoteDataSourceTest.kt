package com.example.solutionxarch.features.login.data.repository.remote

import com.example.solutionxarch.core.common.Utils.BASE_URL
import com.example.solutionxarch.core.data.repository.remote.ApiService
import com.example.solutionxarch.features.login.data.models.request.PhoneRequest
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
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

    private val invalidPhoneNumberRequest = UserRequest(
        PhoneRequest("0020", "012256"), password = "123456789"
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
    fun testRetrofit_postUserRequest() = runTest {
        val response = loginRemoteDataSource.loginUserWithPhone(userRequest)
        assertEquals(true, response.userDto?.email?.isNotEmpty())
    }

    @Test()
    fun testRetrofit_postEmptyRequest() = runTest {
        val response = loginRemoteDataSource.loginUserWithPhone(emptyRequest)
        assertEquals(true, response.token?.isEmpty())
    }

    @Test
    fun testRetrofit_postResponseInvalidPhoneNumber() = runTest {
            val result = loginRemoteDataSource.loginUserWithPhone(invalidPhoneNumberRequest)

            assertEquals(true, result.token?.isNotEmpty())

    }

}










