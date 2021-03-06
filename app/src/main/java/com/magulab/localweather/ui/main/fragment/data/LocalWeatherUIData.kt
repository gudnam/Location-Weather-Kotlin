package com.magulab.localweather.ui.main.fragment.data

data class LocalWeatherUIData(val local: String, val todayWeather: WeatherData, val tomorrowWeather: WeatherData)
data class WeatherData(val stateName: String = "", val stateAbbr: String = "", val temp: String = "", val humidity: String = "")