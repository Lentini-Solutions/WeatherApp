package com.example.cursosant.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cursosant.cities.view.CitiesView
import com.example.cursosant.weather.view.WeatherView

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: Destination = Destination.WEATHER
){
    NavHost(navController = navController, startDestination = destination.route) {
        Destination.entries.forEach { destination ->
            composable(destination.route){
                when(destination){
                    Destination.WEATHER -> WeatherView(modifier)
                    Destination.CITIES -> CitiesView(modifier)
                }
            }
        }
    }
}