package com.example.cursosant.weather.model

import com.example.cursosant.common.constants.Constants
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.entities.WeatherResponse
import com.example.cursosant.common.model.weatherResponseTest
import com.example.cursosant.common.utils.FormatUtils
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RemoteDatabaseTest {

    private lateinit var mockitoService: WeatherService
    private lateinit var rdb: RemoteDatabase

    @Before
    fun setUp() {
        mockitoService = mock<WeatherService>()
        rdb = RemoteDatabase(mockitoService, FormatUtils())
    }

    @Test
    fun searchWeatherByName() = runTest {
        whenever(mockitoService
            .searchWeatherByName(
                Constants.API_KEY, "CMDX", Constants.PARAM_LANG_ES))
            .thenReturn(weatherResponseTest)

       var result = WeatherCity()
       rdb.searchWeatherByName(name = "CMDX") {
           if(it != null) {
               result = it
           }
       }
       assertNotNull(result)
        verify(mockitoService)
            .searchWeatherByName(Constants.API_KEY, "CMDX", Constants.PARAM_LANG_ES)
    }

    @Test
    fun searchWeatherByCoordinates() = runTest {
        whenever(mockitoService
            .getWeatherByCoordinates(
                Constants.API_KEY, "0.0,0.0", Constants.PARAM_LANG_ES))
            .thenReturn(null)

        var result = WeatherCity()
        rdb.getWeatherByCoordinates(coordinates = "0.0,0.0") {
            if(it != null) {
                result = it
            }
        }
        assertNotNull(result)
        verify(mockitoService)
            .getWeatherByCoordinates(
                Constants.API_KEY,
                "0.0,0.0",
                Constants.PARAM_LANG_ES)
    }

}