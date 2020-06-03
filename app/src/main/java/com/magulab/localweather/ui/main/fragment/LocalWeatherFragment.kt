package com.magulab.localweather.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.magulab.localweather.R
import com.magulab.localweather.common.inflate
import com.magulab.localweather.ui.main.fragment.data.LocalWeatherUIData
import com.magulab.localweather.util.getWeatherImageDrawable
import kotlinx.android.synthetic.main.fragment_local_weather.*
import kotlinx.android.synthetic.main.item_local_weather.view.*


class LocalWeatherFragment : Fragment() {
    private val TAG = LocalWeatherFragment::class.java.simpleName

    private lateinit var viewModel: LocalWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LocalWeatherViewModel::class.java)
        bindViewModel()
        return container?.inflate(R.layout.fragment_local_weather)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setEvent()
    }

    override fun onResume() {
        super.onResume()
        viewModel.initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.destroyViewModel()
    }

    private fun addWeatherItem(weatherData: LocalWeatherUIData) {
        val line = getLineView()
        val row = layoutInflater.inflate(R.layout.item_local_weather, null, false)
        row.tv_local.text = weatherData.local
        row.tv_today_state_name.text = weatherData.todayWeather.stateName
        getWeatherImageDrawable(context!!, weatherData.todayWeather.stateAbbr)?.let {
            row.iv_today_weather_img.setImageDrawable(it)
        }
        row.tv_today_temp.text = weatherData.todayWeather.temp
        row.tv_today_humidity.text = weatherData.todayWeather.humidity
        row.tv_tomorrow_state_name.text = weatherData.tomorrowWeather.stateName
        getWeatherImageDrawable(context!!, weatherData.tomorrowWeather.stateAbbr)?.let {
            row.iv_tomorrow_weather.setImageDrawable(it)
        }
        row.tv_tomorrow_temp.text = weatherData.tomorrowWeather.temp
        row.tv_tomorrow_humidity.text = weatherData.tomorrowWeather.humidity

        tl_local_weather.addView(row)
        tl_local_weather.addView(line)
    }

    private fun setEvent() {
    }

    private fun bindViewModel() {
        viewModel.bindLocalWeatherData().observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "$it")
            addWeatherItem(it)
        })
    }

    private fun getLineView(): View {
        val line = View(ContextThemeWrapper(context, R.style.HorizontalLineView))
        line.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3)
        line.setBackgroundResource(R.color.grey900)
        return line
    }
}