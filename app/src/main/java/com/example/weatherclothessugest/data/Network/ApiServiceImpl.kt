package com.example.weatherclothessugest.data.Network


import com.example.weatherapp.models.base.WeatherData
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class ApiServiceImpl:ApiService {
    private val client = WeatherApiClient()
    override fun getWeather(
        onSuccess: (response: WeatherData) -> Unit,
        onFailure: (message: String?) -> Unit,
    ) {
        client.getWeatherData(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e.message)
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val responseWeather =
                        Gson().fromJson(jsonString, WeatherData::class.java)
                    onSuccess(responseWeather)
                }
            }
        })
    }

}