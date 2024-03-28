package com.example.solutionxarch.core.android.helpers.logger

import com.example.solutionxarch.core.android.helpers.logger.writer.DummyWriter

object LoggerFactory {
    var currentLogWriter: LogWriter = DummyWriter.DummyWriter()
        private set

    /**
     * @param clazz the returned logger will be named after clazz
     * @return logger
     */
    fun getLogger(clazz: Class<*>): Logger {
        return Logger(clazz)
    }

    fun setLogWriter(currentLogWriter: LogWriter) {
        LoggerFactory.currentLogWriter = currentLogWriter
    }
}