package com.example.cursosant.cities.model

import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.model.CityDAO
import com.example.cursosant.common.model.WeatherCityDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDatabase(private val cityDAO: CityDAO, private val weatherCityDAO: WeatherCityDAO) {
    fun getRealTimeAllCities(): Flow<List<Location>> =  cityDAO.getRealTimeAllCities()

    suspend fun deleteCityAndWeather(city: Location, onResult: (Boolean) -> Unit) = withContext(Dispatchers.IO){
        onResult(weatherCityDAO.deleteCityAndWeather(city) > 0)
    }
}