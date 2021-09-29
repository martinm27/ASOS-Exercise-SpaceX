package com.acutisbits.asosspacex.core.log

class Lumber private constructor() {

    companion object {
        private lateinit var logger: Logger

        fun initialise(logger: Logger) {
            Companion.logger = logger
        }

        fun d(message: String) = logger.logD(message)
        fun e(message: String, throwable: Throwable? = null) = logger.logE(message, throwable)
        fun i(message: String) = logger.logI(message)
        fun v(message: String) = logger.logV(message)
        fun w(message: String, throwable: Throwable? = null) = logger.logW(message, throwable)
    }
}

interface Logger {
    fun logD(message: String)
    fun logE(message: String, throwable: Throwable? = null)
    fun logI(message: String)
    fun logV(message: String)
    fun logW(message: String, throwable: Throwable? = null)
}
