package com.magulab.localweather.ui.main.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magulab.localweather.common.toUIDataList
import com.magulab.localweather.network.RestAPI
import com.magulab.localweather.network.data.LocalWeatherDatas
import com.magulab.localweather.ui.main.fragment.data.LocalWeatherUIData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class LocalWeatherViewModel: ViewModel() {

    private val TAG = LocalWeatherViewModel::class.java.simpleName
    private val CONTAINS_LOCAL_NAME = "se"

    var disposable = CompositeDisposable()

    private var localWeatherData = MutableLiveData<LocalWeatherUIData>()
    fun bindLocalWeatherData() = localWeatherData


    fun initData() {
        requestLocationData()
    }

    fun destroyViewModel() {
        disposable.dispose()
    }

    private fun requestLocationData() {
        RestAPI.requestGetLocationSearch(CONTAINS_LOCAL_NAME)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError {
                Log.e(TAG, "onError : " + it.message)
            }
            .unsubscribeOn(Schedulers.io())
            .onErrorReturn {
                Log.e(TAG, "onErrorReturn : " + it.message)
                arrayListOf()
            }
            .subscribe { result ->
                Log.i(TAG,"$result")
                requestLocalWeatherData(result[0].woeid)

                result.forEach { data ->
                    requestLocalWeatherData(data.woeid)
                }
            }
            .addTo(disposable)
    }

    private fun requestLocalWeatherData(woeid: Long) {
        RestAPI.requestGetLocationSearch(woeid)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError {
                Log.e(TAG, "onError : " + it.message)
            }
            .unsubscribeOn(Schedulers.io())
            .onErrorReturn {
                Log.e(TAG, "onErrorReturn : " + it.message)
                LocalWeatherDatas(arrayListOf(), "unknown title")
            }
            .subscribe { result ->
                localWeatherData.value = result.toUIDataList()
            }
            .addTo(disposable)
    }

}