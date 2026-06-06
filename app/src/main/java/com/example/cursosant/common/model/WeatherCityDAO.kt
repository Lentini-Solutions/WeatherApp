package com.example.cursosant.common.model

import androidx.room.Dao
import androidx.room.Transaction
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.Weather

@Dao
interface WeatherCityDAO: CityDAO, WeatherDao {

    @Transaction
    suspend fun addCityAndWeather(city: Location, weather: Weather): Long {
        val dbCity = getCityByNameAndCountry(city.name, city.country)
        if(dbCity == null){
            return addWeather(weather = weather.copy(cityId = addCity(city)))
        }else{
            getWeatherByCityId(dbCity.id)?.let { dbWeather ->
                return updateWeather(weather = weather.copy(
                    id = dbWeather.id, cityId = dbWeather.cityId
                )).toLong()
            }
        }
        return 0
    }

    @Transaction
    suspend fun deleteCityAndWeather(city: Location): Int {
        getWeatherByCityId(cityId = city.id)?.let { weather ->
            deleteWeather(weather)
            return deleteCity(city)
        }
        return 0
    }
}