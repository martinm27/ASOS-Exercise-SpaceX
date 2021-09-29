package com.acutisbits.asosspacex.navigation

interface RoutingActionsSource {

    fun setActiveRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)

    fun unsetRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)
}
