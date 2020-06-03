package com.magulab.localweather.network.retrofit

import com.magulab.localweather.network.data.LocalWeatherDatas
import com.magulab.localweather.network.data.LocationData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("api/location/search")
    fun requestGetLocationSearch(
        @Query("query") query: String
    ): Single<List<LocationData>>

    @GET("api/location/{woeid}")
    fun requestGetLocationSearch(
        @Path("woeid") woeid: Long
    ): Single<LocalWeatherDatas>

}