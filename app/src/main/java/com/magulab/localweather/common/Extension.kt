package com.magulab.localweather.common

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magulab.localweather.network.data.LocalWeatherDatas
import com.magulab.localweather.ui.main.fragment.data.LocalWeatherUIData
import com.magulab.localweather.ui.main.fragment.data.WeatherData
import kotlin.math.floor


fun <T> Activity.moveTo(cls: Class<T>) {
    startActivity(Intent(this, cls))
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun String.toTemp(): String {
    return try {
        val floatValue = this.toFloat()
        "${floor(floatValue).toInt()}°C"
    } catch (e: NumberFormatException) {
        "$this°C"
    }
}

fun String.toHumidity(): String {
    return try {
        val floatValue = this.toFloat()
        "${floor(floatValue).toInt()}%"
    } catch (e: NumberFormatException) {
        return "$this%"
    }
}

fun LocalWeatherDatas.toUIDataList(): LocalWeatherUIData {
    if (this.consolidated_weather.size < 2) {
        Log.w("Extension", "$this")
        return LocalWeatherUIData("unknown title", WeatherData(), WeatherData())
    }

    val todayWeather = this.consolidated_weather[0]
    val tomorrowWeather = this.consolidated_weather[1]

    return LocalWeatherUIData(
        this.title,
        WeatherData(todayWeather.weather_state_name, todayWeather.weather_state_abbr, todayWeather.the_temp.toTemp(), todayWeather.humidity.toHumidity()),
        WeatherData(tomorrowWeather.weather_state_name, tomorrowWeather.weather_state_abbr, tomorrowWeather.the_temp.toTemp(), tomorrowWeather.humidity.toHumidity())
    )
}