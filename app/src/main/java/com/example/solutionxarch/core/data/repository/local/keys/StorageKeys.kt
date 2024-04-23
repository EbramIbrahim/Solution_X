package com.example.solutionxarch.core.data.repository.local.keys

import com.example.solutionxarch.core.domain.repository.local.keys.IStorageKeys

enum class StorageKeys(override val key: String): IStorageKeys {
    TOKEN_KEY("token_key"),
    USER_KEY("user_key"),
    LIST_VALUES("list_values")
}