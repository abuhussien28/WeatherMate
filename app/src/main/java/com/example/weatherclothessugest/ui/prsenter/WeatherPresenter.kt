package com.example.weatherclothessugest.ui.prsenter

import android.util.Log
import com.example.weatherapp.models.base.WeatherData
import com.example.weatherclothessugest.data.Network.ApiServiceImpl
import com.example.weatherclothessugest.data.Network.WeatherApiClient


class WeatherPresenter(private val view: IWeatherView) {
    private val service = ApiServiceImpl(WeatherApiClient())
    fun getWeather() {
        service.getWeather(::onSuccess, ::onFailure)
    }

    private fun onSuccess(response: WeatherData) {
        view.onWeatherSuccess(response)
    }

    private fun onFailure(message: String?) {
        Log.d("test",message.toString())
        view.onWeatherFailure(message ?: "")
    }
}