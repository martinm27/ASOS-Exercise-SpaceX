package com.acutisbits.asosspacex.core.log

import timber.log.Timber

class LoggerImpl : Logger {

    override fun logD(message: String) = Timber.d(message)

    override fun logE(message: String, throwable: Throwable?) = Timber.e(throwable, message)

    override fun logI(message: String) = Timber.i(message)

    override fun logV(message: String) = Timber.v(message)

    override fun logW(message: String, throwable: Throwable?) = Timber.w(throwable, message)
}
