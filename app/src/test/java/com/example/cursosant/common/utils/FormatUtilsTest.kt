package com.example.cursosant.common.utils

import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.WeatherResponse
import com.example.cursosant.common.model.weatherResponseTest
import org.junit.Assert.*
import org.junit.Test

class FormatUtilsTest {
    @Test
    fun responseToWeatherCity() {
        val utils = FormatUtils()
        val response = weatherResponseTest
        val result = utils.responseToWeatherCity(response)
        assertNotNull(result)
    }

    @Test
    fun responseToWeatherCityEmptyResponse() {
        val utils = FormatUtils()
        val response = WeatherResponse()
        val result = utils.responseToWeatherCity(response)
        assertNotNull(result!!.name.isEmpty() && result.country.isEmpty())
    }

    @Test
    fun responseToWeatherCityFails(){
        val utils = FormatUtils()
        val response = weatherResponseTest.copy(location = Location(lat = -200.0))
        val result = utils.responseToWeatherCity(response)
        assertNull(result)
    }

}