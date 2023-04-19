package com.example.weatherclothessugest.data.Network

import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class WeatherApiClient() {
    private val client= OkHttpClient.Builder().build()
    fun fetchWeatherData(callback: Callback) {
        val url = buildUrl()
        val request = buildRequest(url)
        client.newCall(request).enqueue(callback)
    }

    private fun buildUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegments("data/2.5/weather")
            .addQueryParameter("lat", LAT)
            .addQueryParameter("lon", LON)
            .addQueryParameter("appid", APP_ID)
            .build()
    }

    private fun buildRequest(url: HttpUrl): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

    companion object {
        private const val SCHEME = "https"
        private const val HOST = "api.openweathermap.org"
        private const val LAT = "30.6987905"
        private const val LON = "31.084785"
        private const val APP_ID = "4ce7ff6dbe5e50ec20fb5fa7a18e9a5c"
    }
}