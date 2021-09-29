package com.acutisbits.asosspacex.navigation

interface RoutingActionConsumer {

    fun onRoutingAction(routingAction: (Router) -> Unit)
}
