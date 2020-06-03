package com.magulab.localweather.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.magulab.localweather.R
import java.lang.Exception

enum class WeatherImageName(val imageName: String) {
    C("c"),
    H("h"),
    HC("hc"),
    HR("hr"),
    LC("lc"),
    LR("lr"),
    S("s"),
    SL("sl"),
    SN("sn"),
    T("t");
}
fun getWeatherImageDrawable(context: Context, name: String): Drawable? {
    var weatherImageName: WeatherImageName?
    try {
        weatherImageName = WeatherImageName.valueOf(name.toUpperCase())
    } catch (e: Exception) {
        return null
    }
    return when (weatherImageName) {
        WeatherImageName.C -> {
            context.getDrawable(R.drawable.c)
        }
        WeatherImageName.H -> {
            context.getDrawable(R.drawable.h)
        }
        WeatherImageName.HC -> {
            context.getDrawable(R.drawable.hc)
        }
        WeatherImageName.HR -> {
            context.getDrawable(R.drawable.hr)
        }
        WeatherImageName.LC -> {
            context.getDrawable(R.drawable.lc)
        }
        WeatherImageName.LR -> {
            context.getDrawable(R.drawable.lr)
        }
        WeatherImageName.S -> {
            context.getDrawable(R.drawable.s)
        }
        WeatherImageName.SL -> {
            context.getDrawable(R.drawable.sl)
        }
        WeatherImageName.SN -> {
            context.getDrawable(R.drawable.sn)
        }
        WeatherImageName.T -> {
            context.getDrawable(R.drawable.t)
        }
    }
}