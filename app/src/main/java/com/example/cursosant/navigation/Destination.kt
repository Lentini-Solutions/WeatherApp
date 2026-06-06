package com.example.cursosant.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.cursosant.R
import com.example.cursosant.common.constants.Constants

enum class Destination(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector,
    val contentDescription: String? = null
) {
    WEATHER(route = Constants.NAV_WEATHER, labelRes = R.string.weather_title, icon = Icons.Default.WbSunny, contentDescription = "Weather View"),
    CITIES(route = Constants.NAV_CITIES, labelRes = R.string.cities_title, icon = Icons.Default.Business, contentDescription = "Cities View"),
}