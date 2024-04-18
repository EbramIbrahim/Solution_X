package com.example.solutionxarch.features.login.data.repository.local

import com.example.solutionxarch.core.domain.repository.local.keys.IStorageKeys

enum class FakeStorageKeys (override val key: String): IStorageKeys {
    TEST_KEY("test_key")
}