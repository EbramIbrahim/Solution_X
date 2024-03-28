package com.example.solutionxarch.core.android.helpers.logger

interface LogWriter {
    fun debug(clazz: Class<*>, message: String?)
    fun info(clazz: Class<*>, message: String?)
    fun warning(clazz: Class<*>, message: String?)
    fun error(clazz: Class<*>, message: String?, throwable: Throwable? = null)

    val isDebugEnabled: Boolean
}