package com.example.cursosant.common.model

import com.example.cursosant.common.entities.Condition
import com.example.cursosant.common.entities.Current
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.Weather
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.entities.WeatherResponse


val cityPreview = Location(0,"Lima", "Perú", 1.2118, -2.1921)

fun getAllCityPreview() = listOf(
    Location(1,"Cdmx", "México", 19.4334565, -99.1331708),
    Location(2,"Madrid", "España", 40.416775, -3.703790),
    cityPreview
)


val weatherCityPreview = WeatherCity(
    31f, "Vientos fuertes",
    22.5f, "", "Lima", "Perú"
)

fun getAllWeatherCityPreview() = listOf(
    WeatherCity(
        21f, "Nublado",
        12.5f, "", "CDMX", "México"
    ),
    WeatherCity(
        21f, "Soleado",
        6f, "", "Madrid", "España"
    ),
    weatherCityPreview
)

//Testing
val weatherTest = Weather(
    id = 1,
    temp_c = 10f,
    iconHttps = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
    description = "Templado",
    wind_kph = 8f,
    cityId = 1
)

val weatherResponseTest = WeatherResponse(
    location = Location(
        name = "Málaga",
        country = "España",
        lat = 36.72016,
        lon = -4.42034
    ),
    current = Current(
        condition = Condition(
            text = "Parcialmente nublado.",
            icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
        )
    )
)

val weatherResponseNullIslandTest = WeatherResponse(
    location = Location(
        name = "Isla Nula",
        country = "N/A",
        lat = 0.0,
        lon = 0.0
    ),
    current = Current(
        condition = Condition(
            text = "Parcialmente nublado.",
            icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
        ))
)

val weatherCityTest = WeatherCity(
    temp_c = 29f,
    iconHttps = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
    description="Parcialmente nublado.",
    wind_kph=13.2f,
    name="Málaga",
    country="España",
    lat=36.72016,
    lon=-4.42034)

val cityNullIslandTest = Location(id = 1, name = "Isla Nula", country = "N/A", lat = 0.0, lon = 0.0)

val weatherCityNullIslandTest = weatherCityTest.copy(name = "Isla Nula", country = "N/A", lat = 0.0, lon = 0.0)
