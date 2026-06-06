package com.example.cursosant.common.utils

import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.Weather
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.entities.WeatherResponse

class FormatUtils {

    fun responseToWeatherCity(response: WeatherResponse): WeatherCity? {
        try {
            if(response.location.lat > 90 || response.location.lat < -90 ||
                response.location.lon > 180 || response.location.lon < -180){
                throw Exception()
            }

            val weatherCity = WeatherCity(
                temp_c = response.current.tempC,
                description = response.current.condition.text,
                wind_kph = response.current.windKph,
                iconHttps = response.current.condition.iconHttps,
                name = response.location.name,
                country = response.location.country,
                lat = response.location.lat,
                lon = response.location.lon
            )

            return weatherCity

        }catch (e: Exception){
            return null
        }
    }

    fun weatherCityToCity(weatherCity: WeatherCity): Location{
        return Location(
            name = weatherCity.name,
            country = weatherCity.country,
            lat = weatherCity.lat,
            lon = weatherCity.lon
        )
    }

    fun weatherCityToWeather(weatherCity: WeatherCity): Weather {
        return Weather(
            temp_c = weatherCity.temp_c,
            description = weatherCity.description,
            wind_kph = weatherCity.wind_kph,
            iconHttps = weatherCity.iconHttps,
        )
    }
}