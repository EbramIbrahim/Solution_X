package com.example.solutionxarch.features.login.domain.usecase

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.core.common.Utils
import com.example.solutionxarch.core.data.repository.local.DataStoreStorageKeyValue
import com.example.solutionxarch.core.data.repository.local.cipher.CryptoManager
import com.example.solutionxarch.core.data.repository.local.cipher.EntitySerializer
import com.example.solutionxarch.core.data.repository.local.cipher.SecureDataStoreStorageKV
import com.example.solutionxarch.core.data.repository.remote.ApiService
import com.example.solutionxarch.core.data.repository.remote.RemoteDataSourceProvider
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.data.models.request.PhoneRequest
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.data.repository.LoginRepositoryImpl
import com.example.solutionxarch.features.login.data.repository.local.LoginLocalDataSource
import com.example.solutionxarch.features.login.data.repository.remote.LoginRemoteDataSource
import kotlinx.coroutines.test.TestScope
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class LoginWithPhoneUCTest {

    private lateinit var testContext: Context

    private lateinit var dataStoreStorageKV: DataStoreStorageKeyValue
    private lateinit var secureDataStoreStorageKV: SecureDataStoreStorageKV<UserEntity>

    private lateinit var remoteDataSourceProvider: RemoteDataSourceProvider
    private lateinit var apiServer: ApiService

    private lateinit var loginLocalDataSource: LoginLocalDataSource
    private lateinit var loginRemoteDataSource: LoginRemoteDataSource

    private val coroutineTestRule = TestScope()


    lateinit var repositoryImpl: LoginRepositoryImpl

    lateinit var loginWithPhoneUC: LoginWithPhoneUC

    private val userRequest = UserRequest(PhoneRequest("0020", "100100100"), password = "123456789")


    @Before
    fun setUp() {
        apiServer = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

        testContext = ApplicationProvider.getApplicationContext()
        dataStoreStorageKV = DataStoreStorageKeyValue(testContext)
        secureDataStoreStorageKV = SecureDataStoreStorageKV(
            testContext,
            EntitySerializer(CryptoManager(), UserEntity.serializer()) { UserEntity() }
        )
        remoteDataSourceProvider = RemoteDataSourceProvider(apiServer)

        loginLocalDataSource = LoginLocalDataSource(secureDataStoreStorageKV, dataStoreStorageKV)
        loginRemoteDataSource = LoginRemoteDataSource(remoteDataSourceProvider)

        repositoryImpl = LoginRepositoryImpl(loginRemoteDataSource, loginLocalDataSource)

        loginWithPhoneUC = LoginWithPhoneUC(repositoryImpl)
    }


    @Test
    fun userLogin_LoginWithPhoneUC_Success() {
        loginWithPhoneUC(
            coroutineTestRule,
            needLoading = true,
            request = userRequest,
            onResult = { result ->
                if (result is Result.Success) {
                    assertEquals(true, result.data.token.isNotEmpty())
                }
            }
        )
    }

}