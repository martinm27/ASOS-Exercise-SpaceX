package com.acutisbits.asosspacex.data.usecase

import com.acutisbits.asosspacex.core.EMPTY_STRING
import com.acutisbits.asosspacex.core.UNKNOWN_STRING
import com.acutisbits.asosspacex.core.usecase.QueryUseCase
import com.acutisbits.asosspacex.data.model.api.APILaunch
import com.acutisbits.asosspacex.data.model.api.APIRocket
import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.data.model.domain.Rocket
import com.acutisbits.asosspacex.data.network.ASOSSpaceXService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class QueryAllLaunches(private val service: ASOSSpaceXService) : QueryUseCase<List<Launch>> {

    override fun invoke(): Flow<List<Launch>> =
        flow {
            val allLaunches = service.getAllLaunches(null)
            emit(mapToReadableData(allLaunches))
        }.catch {
            emit(emptyList())
        }

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
}
