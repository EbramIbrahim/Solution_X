package com.example.solutionxarch.core.presentation.helper.state_logger

import androidx.lifecycle.LifecycleOwner

interface IStateLogger {
    fun registerLifecycleOwner(lifecycleOwner: LifecycleOwner)

}