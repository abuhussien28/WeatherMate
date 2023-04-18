package com.example.weatherclothessugest.data.Network


import com.example.weatherapp.models.base.WeatherData

interface ApiService {
    fun getWeather(
        onSuccess: (response: WeatherData) -> Unit,
        onFailure: (message: String?) -> Unit,
    )
}