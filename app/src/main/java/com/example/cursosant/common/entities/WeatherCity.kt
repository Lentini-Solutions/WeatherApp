package com.example.cursosant.common.entities

data class WeatherCity(
    val temp_c: Float = 0.0f,
    val description: String = "",
    val wind_kph: Float = 0.0f,
    val name: String = "",
    val country: String = "",
    val iconHttps: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0
)
