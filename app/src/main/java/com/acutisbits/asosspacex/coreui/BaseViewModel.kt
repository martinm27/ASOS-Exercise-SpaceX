package com.acutisbits.asosspacex.coreui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.acutisbits.asosspacex.core.log.Lumber
import com.acutisbits.asosspacex.core.mutableSharedFlow
import com.acutisbits.asosspacex.core.mutableSharedFlowWithLatest
import com.acutisbits.asosspacex.core.safeCoroutineExceptionHandler
import com.acutisbits.asosspacex.navigation.Router
import com.acutisbits.asosspacex.navigation.RoutingActionsDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlin.reflect.KClass

abstract class BaseViewModel<BaseViewState : Any>(private val routingActionsDispatcher: RoutingActionsDispatcher) : ViewModel() {

    private val typeToPublisher: MutableMap<KClass<BaseViewState>, MutableSharedFlow<BaseViewState>> = mutableMapOf()

    private val bgScope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    init {
        Lumber.d("Init ${this::class.simpleName}")
    }

    fun viewStates(): Collection<Flow<BaseViewState>> = typeToPublisher.values

    @Suppress("UNCHECKED_CAST")
    protected inline fun <reified T : BaseViewState> query(flow: Flow<T>) =
        query(T::class as KClass<BaseViewState>, flow, ::mutableSharedFlowWithLatest)

    @Suppress("UNCHECKED_CAST")
    protected inline fun <reified T : BaseViewState> queryEvents(flow: Flow<T>) = query(T::class as KClass<BaseViewState>, flow, ::mutableSharedFlow)

    fun runCommand(command: suspend () -> Unit) {
        bgScope.launch(
            safeCoroutineExceptionHandler { _, throwable ->
                Lumber.e("Executing ${this@BaseViewModel::class.simpleName} ${command::class.simpleName} failed", throwable)
            }
        ) {
            Lumber.d("Executing command in ${this@BaseViewModel::class.simpleName} ${command::class.simpleName}")
            command()
        }
    }

    /** Invoke to route to another screen */
    protected fun dispatchRoutingAction(routingAction: (Router) -> Unit) =
        routingActionsDispatcher.dispatch(routingAction)

    /** Invoke to route to another screen. If another routing action with the same [actionId] is already queued, the old one will be removed. */
    protected fun dispatchDistinctRoutingAction(actionId: String, routingAction: (Router) -> Unit) =
        routingActionsDispatcher.dispatchDistinct(actionId, routingAction)

    @Deprecated("Internal usage only! Visible because of inlining")
    protected fun query(viewStateClass: KClass<BaseViewState>, flow: Flow<BaseViewState>, publisherFactory: () -> MutableSharedFlow<BaseViewState>) {
        if (typeToPublisher.contains(viewStateClass)) throw IllegalArgumentException("Flow<${viewStateClass.simpleName}> already registered")

        val viewStatePublisher = publisherFactory()
        typeToPublisher[viewStateClass] = viewStatePublisher

        bgScope.launch(
            safeCoroutineExceptionHandler { _, throwable ->
                Lumber.e("Query ${this@BaseViewModel::class.simpleName} ${viewStateClass.simpleName} failed", throwable)
            }
        ) {
            flow
                .onEach { Lumber.d("New emission of ${this@BaseViewModel::class.simpleName} {${viewStateClass.simpleName} $it}") }
                .collect { viewStatePublisher.tryEmit(it) }
        }
    }

    @CallSuper
    override fun onCleared() {
        Lumber.d("Deinit ${this::class.simpleName}")
        bgScope.cancel()
    }

    fun back() = dispatchRoutingAction(Router::goBack)
}
