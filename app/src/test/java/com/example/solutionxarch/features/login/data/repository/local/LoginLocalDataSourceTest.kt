package com.example.solutionxarch.features.login.data.repository.local

import com.example.solutionxarch.core.data.repository.local.DataStoreStorageKeyValue
import com.example.solutionxarch.core.data.repository.local.cipher.SecureDataStoreStorageKV
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class LoginLocalDataSourceTest {


    private val secureDataSourceProvider = mockk<SecureDataStoreStorageKV<UserEntity>>(relaxed = true)
    private val dataSourceProvider = mockk<DataStoreStorageKeyValue>(relaxed = true)
    private val loginLocalDataSource = LoginLocalDataSource(secureDataSourceProvider, dataSourceProvider)



    @Test
    fun testDatastore_getDataForFirstTime_returnEmpty() = runTest {
        val user = UserEntity(username = "Ebram", token = "token", email = "a@a.com", id = 1)

        coEvery { secureDataSourceProvider.read() } returns user
        val result = loginLocalDataSource.getUser()

        assertEquals(result.token, user.token)
    }

    @Test
    fun testDataStore_saveAndGetDataFromLocal() = runTest {
        val user = UserEntity(username = "Ebram", token = "token", email = "a@a.com", id = 1)

        loginLocalDataSource.saveUser(user)
        val captor = slot<UserEntity>()

        coVerify { secureDataSourceProvider.secureSave(capture(captor)) }
        assertEquals(user, captor.captured)
    }



}