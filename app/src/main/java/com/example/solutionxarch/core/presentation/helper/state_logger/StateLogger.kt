package com.example.solutionxarch.core.presentation.helper.state_logger

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.solutionxarch.core.android.helpers.logger.writer.LogcatWriter

class StateLogger: IStateLogger, LifecycleEventObserver {

    private val logger = LogcatWriter("Tag", true)


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> {
                logger.debug(String::class.java, "User enter the application")
            }
            Lifecycle.Event.ON_PAUSE -> {
                logger.debug(String::class.java, "User leave the application")
            }
            else -> Unit
        }
    }

    override fun registerLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)
    }
}