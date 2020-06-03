package com.magulab.localweather.network.data

data class LocalWeatherDatas(val consolidated_weather: List<LocalWeatherData>)
data class LocalWeatherData(val weather_state_name: String, val weather_state_abbr: String, val the_temp: String, val humidity: String)
