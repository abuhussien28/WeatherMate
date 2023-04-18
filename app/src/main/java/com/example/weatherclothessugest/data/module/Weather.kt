package com.example.weatherapp.models.base

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)