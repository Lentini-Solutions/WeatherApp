package com.example.cursosant.cities.viewmodel

import com.example.cursosant.R
import com.example.cursosant.common.entities.Location

data class CityUiState(
    val items: List<Location> = emptyList(),
    val inProgress: Boolean = false,
    val msgRes: Int = R.string.empty_msg
)
