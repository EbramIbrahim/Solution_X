package com.example.solutionxarch.features.login.data.repository.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginLocalDataSourceTest {

    private lateinit var testContext: Context
    private lateinit var fakeLocalDataSource: FakeLocalDataSource
    private val testValue = "Dummy_String"

    @Before
    fun setup() {
        testContext = ApplicationProvider.getApplicationContext()
        fakeLocalDataSource = FakeLocalDataSource(testContext)
    }

    @Test
    fun datastore_getDataForFirstTime() = runTest {
        val result = fakeLocalDataSource.read(FakeStorageKeys.TEST_KEY, "")
        assertEquals("", result)
    }

    @Test
    fun dataStore_saveAndGetDataFromLocal() = runTest {
        fakeLocalDataSource.save(FakeStorageKeys.TEST_KEY, testValue)
        val result = fakeLocalDataSource.read(FakeStorageKeys.TEST_KEY, "")
        assertEquals(testValue, result)
    }





}