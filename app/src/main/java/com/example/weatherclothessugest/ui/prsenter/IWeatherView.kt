package com.example.weatherclothessugest.ui.prsenter

import com.example.weatherapp.models.base.WeatherData



interface IWeatherView{
   fun onWeatherSuccess(Data:WeatherData)
   fun onWeatherFailure(error: String)
}