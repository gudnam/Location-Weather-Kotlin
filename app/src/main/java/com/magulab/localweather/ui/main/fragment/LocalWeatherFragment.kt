package com.magulab.localweather.ui.main.fragment

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.magulab.localweather.R
import com.magulab.localweather.common.inflate
import kotlinx.android.synthetic.main.fragment_local_weather.*


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
        initView()
        setEvent()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.destroyViewModel()
    }

    private fun initView() {
        val row = layoutInflater.inflate(R.layout.item_local_weather, null, false)
        val row2 = layoutInflater.inflate(R.layout.item_local_weather, null, false)
        val row3 = layoutInflater.inflate(R.layout.item_local_weather, null, false)
        val line = View(ContextThemeWrapper(context, R.style.HorizontalLineView))
        line.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3)
        line.setBackgroundResource(R.color.grey900)
        tl_local_weather.addView(row)
        tl_local_weather.addView(row2)
        tl_local_weather.addView(line)
        tl_local_weather.addView(row3)
    }

    private fun setEvent() {
    }

    private fun bindViewModel() {
    }
}