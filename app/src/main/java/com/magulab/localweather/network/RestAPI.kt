package com.magulab.localweather.network

import com.magulab.localweather.network.data.LocalWeatherDatas
import com.magulab.localweather.network.data.LocationData
import com.magulab.localweather.network.retrofit.RetrofitRestAPI
import com.magulab.localweather.network.retrofit.RetrofitService
import io.reactivex.Single

object RestAPI {

    private val BASE_URL = "http://www.metaweather.com"
    private val TIMEOUT = 10L

    private val service: RetrofitService = RetrofitRestAPI(
        BASE_URL,
        TIMEOUT
    ).getService()

    fun requestGetLocationSearch(query: String): Single<List<LocationData>> {
        return service.requestGetLocationSearch(query)
    }

    fun requestGetLocationSearch(woeid: Long): Single<LocalWeatherDatas> {
        return service.requestGetLocationSearch(woeid)
    }
}