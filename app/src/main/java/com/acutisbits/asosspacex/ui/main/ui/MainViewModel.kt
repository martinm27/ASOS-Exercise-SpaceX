package com.acutisbits.asosspacex.ui.main.ui

import com.acutisbits.asosspacex.coreui.BaseViewModel
import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.data.usecase.QueryAllLaunches
import com.acutisbits.asosspacex.navigation.RoutingActionsDispatcher
import com.acutisbits.asosspacex.ui.main.model.LaunchViewState
import com.acutisbits.asosspacex.ui.main.model.MainViewState
import kotlinx.coroutines.flow.map

class MainViewModel(
    queryAllLaunches: QueryAllLaunches,
    routingActionsDispatcher: RoutingActionsDispatcher
) : BaseViewModel<MainViewState>(routingActionsDispatcher) {

    init {
        query(queryAllLaunches().map {
            MainViewState(
                "",
                it.map(::toLaunchViewState)
            )
        })
    }

    private fun toLaunchViewState(launch: Launch): LaunchViewState =
        with(launch) {
            LaunchViewState(
                id,
                "",
                missionName,
                "",
                "",
                rocket?.name ?: "",
                "",
                "",
                isLaunchSuccessful
            )
        }

}
