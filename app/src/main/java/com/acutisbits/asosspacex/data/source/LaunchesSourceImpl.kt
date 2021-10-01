package com.acutisbits.asosspacex.data.source

import com.acutisbits.asosspacex.core.EMPTY_STRING
import com.acutisbits.asosspacex.core.UNKNOWN_STRING
import com.acutisbits.asosspacex.core.mutableSharedFlowWithLatest
import com.acutisbits.asosspacex.data.model.api.APILaunch
import com.acutisbits.asosspacex.data.model.api.APIRocket
import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.data.model.domain.Rocket
import com.acutisbits.asosspacex.data.network.ASOSSpaceXService
import com.acutisbits.asosspacex.util.SortingOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

class LaunchesSourceImpl(private val service: ASOSSpaceXService) : LaunchesSource {

    private val launchesListPublisher = mutableSharedFlowWithLatest<List<Launch>>()

    private val launchesListFlow = launchesListPublisher
        .onStart { emit(getLaunchesList()) }
        .distinctUntilChanged()

    private suspend fun getLaunchesList(): List<Launch> = mapToReadableData(service.getAllLaunches(null))

    private fun mapToReadableData(response: Response<List<APILaunch>>): List<Launch> =
        if (response.isSuccessful) {
            response.body()?.map { toLaunches(it) } ?: emptyList()
        } else {
            emptyList()
        }

    private fun toLaunches(apiLaunch: APILaunch): Launch =
        with(apiLaunch) {
            Launch(
                id ?: 0,
                links?.missionImageUrl ?: EMPTY_STRING,
                missionName ?: UNKNOWN_STRING,
                links?.articleLink ?: EMPTY_STRING,
                links?.wikipediaLink ?: EMPTY_STRING,
                links?.videoLink ?: EMPTY_STRING,
                isUpcoming ?: false,
                launchDate ?: UNKNOWN_STRING,
                mapRocket(rocket),
                isLaunchSuccessful ?: false
            )
        }

    private fun mapRocket(apiRocket: APIRocket?): Rocket? =
        apiRocket?.let {
            Rocket(it.id, it.name, it.type)
        }

    override fun getAllLaunches() = launchesListFlow

    override fun getLaunchesSorted(comparator: Comparator<Launch>, sortingOrder: SortingOrder): Flow<List<Launch>> {
        return emptyFlow()
        // TODO: Add list of
    }

}
