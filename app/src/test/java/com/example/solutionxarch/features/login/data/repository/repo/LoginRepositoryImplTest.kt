package com.example.solutionxarch.features.login.data.repository.repo

import com.example.solutionxarch.features.login.data.models.dto.UserLoginDto
import com.example.solutionxarch.features.login.data.models.request.PhoneRequest
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.features.login.domain.models.User
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class LoginRepositoryImplTest {


    private val userLoginDto = UserLoginDto("message", "token", null)
    private val userRequest = UserRequest(PhoneRequest("0020", "100100100"), password = "123456789")
    private lateinit var fakeLoginRemoteDS: FakeLoginRemoteDS
    private lateinit var fakeLoginLocalDs: FakeLoginLocalDs
    private lateinit var loginRepositoryImpl: LoginRepositoryImpl
    private val user = User("Ebram", "token", "e@e.com", 1)


    @Before
    fun setUp() {
        fakeLoginRemoteDS = FakeLoginRemoteDS(userLoginDto)
        fakeLoginLocalDs = FakeLoginLocalDs()
        loginRepositoryImpl = LoginRepositoryImpl(fakeLoginRemoteDS, fakeLoginLocalDs)
    }



    @Test
    fun testRepository_getUserData_requestUserDataFromRemote() = runTest {
        val user = loginRepositoryImpl.loginUserWithPhone(userRequest)
        assertEquals(userLoginDto.token, user.token)
    }

    @Test
    fun testRepository_getUserData_requestUserDataFromLocal() = runTest {
        loginRepositoryImpl.saveUser(user)
        val result = loginRepositoryImpl.getUser()
        assertEquals(result.token, user.token)
    }


}














