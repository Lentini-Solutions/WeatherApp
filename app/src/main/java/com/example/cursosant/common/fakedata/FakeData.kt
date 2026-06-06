package com.example.cursosant.common.fakedata

import com.example.cursosant.common.entities.Location

fun getAllCitiesPreview() = listOf(
    Location(name = "Cdmx", country = "Mexico", lat = 19.4334565, lon = -99.13311708),
    Location(name = "Madrid", country = "Spain", lat = 40.416775, lon = -3.703790),
)

fun getCityPreview() = Location(name = "Cdmx", country = "Mexico", lat = 19.4334565, lon = -99.13311708)