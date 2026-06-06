package com.example.cursosant.common.entities

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("temp_c") val tempC: Float = 0.0f,
    @SerializedName("temp_f") val tempF: Float = 0.0f,
    val condition: Condition = Condition(),
    @SerializedName("wind_mph") val windMph: Float = 0.0f,
    @SerializedName("wind_kph") val windKph: Float = 0.0f,
)
