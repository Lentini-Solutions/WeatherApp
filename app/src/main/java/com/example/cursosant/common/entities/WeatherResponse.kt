package com.example.cursosant.common.entities

data class WeatherResponse(
    val location: Location = Location(),
    val current: Current = Current()
)
