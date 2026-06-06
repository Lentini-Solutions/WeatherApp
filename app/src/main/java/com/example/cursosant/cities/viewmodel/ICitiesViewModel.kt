package com.example.cursosant.cities.viewmodel

import com.example.cursosant.common.entities.Location
import kotlinx.coroutines.flow.StateFlow

interface ICitiesViewModel {
    fun getUiState(): StateFlow<CityUiState>
    fun showMap(city: Location)
    fun clearMsg()
    fun deleteCity(city: Location)
}