package com.acutisbits.asosspacex.data.network

import com.acutisbits.asosspacex.data.model.api.APILaunch
import com.acutisbits.asosspacex.data.model.api.APIRocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ASOSSpaceXService {

    @GET("/v3/launches")
    suspend fun getAllLaunches(@Query("limit") limit: Int?): Response<List<APILaunch>>
}
