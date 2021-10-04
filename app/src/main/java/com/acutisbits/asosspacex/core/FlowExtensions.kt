package com.acutisbits.asosspacex.core

import com.acutisbits.asosspacex.core.log.Lumber
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.isActive
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/** Factory method for MutableSharedFlow with PublishSubject like behaviour */
fun <T> mutableSharedFlow() = MutableSharedFlow<T>(extraBufferCapacity = 1)

/** Factory method for MutableSharedFlow with BehaviorSubject-without-initial-value like behaviour */
fun <T> mutableSharedFlowWithLatest() = MutableSharedFlow<T>(replay = 1, extraBufferCapacity = 1)

inline fun safeCoroutineExceptionHandler(crossinline handler: (CoroutineContext, Throwable) -> Unit): CoroutineExceptionHandler =
    object : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            if (context.isActive) handler.invoke(context, exception)
            else Lumber.w("Error occurred but the consumer is no longer active", exception)
        }
    }
