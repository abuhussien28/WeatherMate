package com.example.weatherclothessugest.data.Network

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class WeatherApiClient {
    private val client = OkHttpClient()
    fun getWeatherData(callback: Callback) {
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=30.6987905&lon=31.084785&appid=4ce7ff6dbe5e50ec20fb5fa7a18e9a5c"
        val request = Request.Builder()
            .url(url)
            .build()
       return client.newCall(request).enqueue(callback)
    }
}