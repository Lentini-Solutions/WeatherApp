package com.example.cursosant.weather.model

import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.model.CityDAO
import com.example.cursosant.common.model.WeatherCityDAO
import com.example.cursosant.common.model.WeatherDao
import com.example.cursosant.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDatabase(
    private val cityDao: CityDAO,
    private val utils: FormatUtils,
    private val weatherDao: WeatherDao,
    private val weatherCityDAO: WeatherCityDAO,
) {

    suspend fun getAllCities(onResult: (List<Location>) -> Unit) = withContext(Dispatchers.IO){
        onResult(cityDao.getAllCities())
    }

    suspend fun addWeatherAndCity(weatherCity: WeatherCity, onResult: (Boolean) -> Unit) = withContext(Dispatchers.IO){
        val city = utils.weatherCityToCity(weatherCity)
        val weather = utils.weatherCityToWeather(weatherCity)
        val result = weatherCityDAO.addCityAndWeather(city, weather)
        onResult(result > 0)
    }

    suspend fun getWeatherCityById(cityId: Long, onResult: (WeatherCity?) -> Unit) = withContext(Dispatchers.IO) {
            onResult(weatherDao.getWeatherCityByCityId(cityId))
    }
}