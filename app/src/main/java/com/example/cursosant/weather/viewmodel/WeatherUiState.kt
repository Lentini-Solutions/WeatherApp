package com.example.cursosant.weather.viewmodel

import com.example.cursosant.R
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.WeatherCity

data class WeatherUiState(
    val data: WeatherCity = WeatherCity(),
    val items: List<Location> = emptyList(),
    val inProgress: Boolean = false,
    val msgRes: Int = R.string.empty_msg)
