package com.example.cursosant.weather.model

import com.example.cursosant.common.constants.Constants
import com.example.cursosant.common.entities.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherService {

    @GET("${Constants.PATH_V1}$")
    suspend fun searchWeatherByName(
        @Query(Constants.PARAM_KEY) key: String,
        @Query(Constants.PARAM_QUERY) name: String,
        @Query(Constants.PARAM_LANG) lang: String
    ): WeatherResponse

    @GET("${Constants.PATH_V1}$")
    suspend fun getWeatherByCoordinates(
        @Query(Constants.PARAM_KEY) key: String,
        @Query(Constants.PARAM_QUERY) coordinates: String,
        @Query(Constants.PARAM_LANG) lang: String
    ): WeatherResponse


}