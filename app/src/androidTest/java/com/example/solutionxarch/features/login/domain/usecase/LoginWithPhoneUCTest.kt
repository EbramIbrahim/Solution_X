package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.features.login.data.models.request.PhoneRequest
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.TestScope
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class LoginWithPhoneUCTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val coroutineTestRule = TestScope()


    @Inject
    @Named("repository_test")
    lateinit var repositoryImpl: LoginRepositoryImpl

    lateinit var loginWithPhoneUC: LoginWithPhoneUC

    private val userRequest = UserRequest(PhoneRequest("0020", "100100100"), password = "123456789")


    @Before
    fun setUp() {
        hiltRule.inject()
        loginWithPhoneUC = LoginWithPhoneUC(repositoryImpl)
    }


    @Test
    fun userLogin_LoginWithPhoneUC() {
        loginWithPhoneUC(
            coroutineTestRule,
            needLoading = true,
            request = userRequest,
            onResult = { result ->
                when (result) {
                    is Result.Failure -> {
                        Assert.assertEquals(false, result.error.message?.isEmpty())
                    }
                    is Result.Loading -> TODO()

                    is Result.Success -> TODO()
                }
            })
    }

}