package com.example.solutionxarch.core.android.helpers.logger.writer

import com.example.solutionxarch.core.android.helpers.logger.LogWriter

class DummyWriter {
    class DummyWriter : LogWriter {
        override val isDebugEnabled: Boolean
            get() = false

        override fun debug(clazz: Class<*>, message: String?) {}

        override fun info(clazz: Class<*>, message: String?) {}

        override fun warning(clazz: Class<*>, message: String?) {}

        override fun error(clazz: Class<*>, message: String?, throwable: Throwable?) {}
    }
}