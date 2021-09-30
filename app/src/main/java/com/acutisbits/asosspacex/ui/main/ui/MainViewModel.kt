package com.acutisbits.asosspacex.ui.main.ui

import android.content.res.Resources
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.core.UNKNOWN_STRING
import com.acutisbits.asosspacex.coreui.BaseViewModel
import com.acutisbits.asosspacex.data.model.domain.CompanyInfo
import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.data.usecase.QueryAllLaunches
import com.acutisbits.asosspacex.data.usecase.QueryCompanyInfo
import com.acutisbits.asosspacex.navigation.RoutingActionsDispatcher
import com.acutisbits.asosspacex.ui.main.model.LaunchViewState
import com.acutisbits.asosspacex.ui.main.model.MainViewState
import kotlinx.coroutines.flow.map

class MainViewModel(
    private val resources: Resources,
    queryCompanyInfo: QueryCompanyInfo,
    queryAllLaunches: QueryAllLaunches,
    routingActionsDispatcher: RoutingActionsDispatcher
) : BaseViewModel<MainViewState>(routingActionsDispatcher) {

    init {
        query(queryCompanyInfo().map {
            MainViewState.CompanyViewState(
                buildCompanyDescription(it)
            )
        })

        query(queryAllLaunches().map {
            MainViewState.LaunchesViewState(
                it.map(::toLaunchViewState)
            )
        })
    }

    private fun buildCompanyDescription(companyInfo: CompanyInfo): String =
        if (companyInfo == CompanyInfo.EMPTY) {
            UNKNOWN_STRING
        } else {
            with(companyInfo) {
                String.format(
                    resources.getString(R.string.company_info_description),
                    name,
                    founder,
                    year,
                    employeesNumber.toString(),
                    launchSitesNumber.toString(),
                    valuation
                )
            }
        }


    private fun toLaunchViewState(launch: Launch): LaunchViewState =
        with(launch) {
            LaunchViewState(
                id,
                missionImageUrl,
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
