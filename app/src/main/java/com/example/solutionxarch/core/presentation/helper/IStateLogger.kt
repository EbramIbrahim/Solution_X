package com.example.solutionxarch.core.presentation.helper

import androidx.lifecycle.LifecycleOwner

interface IStateLogger {
    fun registerLifecycleOwner(lifecycleOwner: LifecycleOwner)

}