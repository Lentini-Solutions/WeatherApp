package com.example.cursosant.weather.domain

import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.utils.NetworkUtils
import com.example.cursosant.weather.model.LocalDatabase
import com.example.cursosant.weather.model.RemoteDatabase

class DataSource(private val rdb: RemoteDatabase,
                 private val ldb: LocalDatabase,
                 private val network: NetworkUtils
) {

    suspend fun getAllCities(
        onResult: (
            List<Location>
        ) -> Unit
    ) = ldb.getAllCities { onResult(it) }

    suspend fun searchWeatherByName(name: String, onResult: (WeatherCity?) -> Unit) =
        rdb.searchWeatherByName(name) { onResult(it) }

    suspend fun addWeatherAndCity(weatherCity: WeatherCity, onResult: (Boolean) -> Unit) =
        ldb.addWeatherAndCity(weatherCity) { onResult(it) }

    suspend fun getWeatherByCity(city: Location, onResult: (WeatherCity?) -> Unit) {
        if (network.isOnline()) {
            //rdb.searchWeatherByName(city.name) { onResult(it) }
            rdb.getWeatherByCoordinates(coordinates = "${city.lat},${city.lon}") { onResult(it) }
        } else {
            ldb.getWeatherCityById(city.id) { onResult(it) }
        }
    }
}