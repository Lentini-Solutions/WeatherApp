package com.example.cursosant.weather.model

import com.example.cursosant.common.constants.Constants
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase(
    private val service: WeatherService,
    private val utils: FormatUtils
) {
    suspend fun searchWeatherByName(name: String, onResult: (WeatherCity?) -> Unit) =
        withContext(Dispatchers.IO){
        try {
            val result = service
                .searchWeatherByName(
                    key = Constants.API_KEY,
                    name = name, lang =
                        Constants.PARAM_LANG_ES
                )
            onResult(utils.responseToWeatherCity(result))
        }catch (_: Exception){
            onResult(null)
        }
    }

    suspend fun getWeatherByCoordinates(coordinates: String, onResult: (WeatherCity?) -> Unit) =
        withContext(Dispatchers.IO){
            try {
                val result = service
                    .getWeatherByCoordinates(
                        key = Constants.API_KEY,
                        coordinates = coordinates, lang =
                            Constants.PARAM_LANG_ES
                    )
                onResult(utils.responseToWeatherCity(result))
            }catch (_: Exception){
                onResult(null)
            }
        }
}