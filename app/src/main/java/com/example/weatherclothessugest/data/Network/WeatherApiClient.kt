package com.example.weatherclothessugest.data.Network

import com.example.weatherclothessugest.BuildConfig
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
            .host(BuildConfig.HOST)
            .addPathSegments(PATH)
            .addQueryParameter("lat", LAT)
            .addQueryParameter("lon", LON)
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()
    }

    private fun buildRequest(url: HttpUrl): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

    companion object {
        private const val SCHEME = "https"
        private const val LAT = "30.6987905"
        private const val LON = "31.084785"
        private const val PATH = "data/2.5/weather"
    }
}