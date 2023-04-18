package com.example.weatherclothessugest.ui.prsenter

import com.example.weatherapp.models.base.WeatherData


interface IView
interface IWeatherView:IView{
   fun onWeatherSuccess(Data:WeatherData)
   fun onWeatherFailure(error: String)
}