package com.example.weatherapp.models.base

data class WeatherData(
    val base: String,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)