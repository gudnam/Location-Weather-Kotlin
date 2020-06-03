package com.magulab.localweather.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.magulab.localweather.R
import com.magulab.localweather.ui.main.fragment.LocalWeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    private fun setView() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.cl_local_weather, LocalWeatherFragment()).addToBackStack(null).commit()
    }

}
