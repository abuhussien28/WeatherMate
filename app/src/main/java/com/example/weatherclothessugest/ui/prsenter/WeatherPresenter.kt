package com.example.weatherclothessugest.ui.prsenter

import com.example.weatherapp.models.base.WeatherData
import com.example.weatherclothessugest.data.Network.ApiServiceImpl


class WeatherPresenter(private val view: IWeatherView) {
    private val service = ApiServiceImpl()


    fun getWeather() {
        service.getWeather( ::onSuccess, ::onFailure)
    }

    private fun onSuccess(response: WeatherData) {
        view.onWeatherSuccess(response)
    }

    private fun onFailure(message: String?) {
        view.onWeatherFailure(toString())
    }
}