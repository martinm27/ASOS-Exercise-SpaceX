package com.acutisbits.asosspacex.ui.main.ui

import android.content.res.Resources
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.core.UNKNOWN_STRING
import com.acutisbits.asosspacex.coreui.BaseViewModel
import com.acutisbits.asosspacex.data.model.domain.CompanyInfo
import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.data.usecase.QueryAllLaunches
import com.acutisbits.asosspacex.data.usecase.QueryCompanyInfo
import com.acutisbits.asosspacex.data.usecase.SortLaunches
import com.acutisbits.asosspacex.data.usecase.SortLaunchesParam
import com.acutisbits.asosspacex.navigation.RoutingActionsDispatcher
import com.acutisbits.asosspacex.ui.main.model.LaunchViewState
import com.acutisbits.asosspacex.ui.main.model.MainViewState
import com.acutisbits.asosspacex.ui.main.model.MainViewState.*
import com.acutisbits.asosspacex.util.date.DateUtils
import com.acutisbits.asosspacex.util.sort.SortingOrder
import com.acutisbits.asosspacex.util.sort.SortingType
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart

class MainViewModel(
    private val resources: Resources,
    private val dateUtils: DateUtils,
    private val sortLaunches: SortLaunches,
    queryCompanyInfo: QueryCompanyInfo,
    queryAllLaunches: QueryAllLaunches,
    routingActionsDispatcher: RoutingActionsDispatcher
) : BaseViewModel<MainViewState>(routingActionsDispatcher) {

    init {
        query(
            queryCompanyInfo()
                .combine(queryAllLaunches()) { companyInfo, launches ->
                    if (launches.isEmpty() || companyInfo == CompanyInfo.EMPTY) {
                        ErrorViewState
                    } else {
                        ResultViewState(buildCompanyDescription(companyInfo), launches.map(::toLaunchViewState))
                    }
                }
                .onStart { emit(LoadingViewState) }
        )
    }

    private fun buildCompanyDescription(companyInfo: CompanyInfo): String =
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


    private fun toLaunchViewState(launch: Launch): LaunchViewState =
        with(launch) {
            LaunchViewState(
                id,
                missionImageUrl,
                missionName,
                missionArticleUrl,
                missionWikipediaUrl,
                missionVideoUrl,
                String.format(
                    resources.getString(R.string.launch_date_time_value),
                    dateUtils.getFormattedDate(launchDate),
                    dateUtils.getFormattedTime(launchDate)
                ),
                "${rocket?.name ?: UNKNOWN_STRING} / ${rocket?.type ?: UNKNOWN_STRING}",
                String.format(
                    resources.getString(R.string.launch_days_key),
                    resources.getString(if (dateUtils.isDateInFuture(launchDate)) R.string.from else R.string.since)
                ),
                dateUtils.getDateDifferenceFromTodayInDays(launchDate),
                isLaunchSuccessful
            )
        }

    fun showOpenLinkDialog(articleUrl: String, wikipediaUrl: String, videoUrl: String) =
        dispatchRoutingAction { it.showOpenLinkDialog(articleUrl, wikipediaUrl, videoUrl) }

    fun tryAgain() {

    }

    fun sortList(sortingType: SortingType, sortingOrder: SortingOrder) {
        runCommand {
            sortLaunches(SortLaunchesParam(sortingType, sortingOrder))
        }
    }
}
