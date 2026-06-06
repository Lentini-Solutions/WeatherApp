package com.example.cursosant.weather.di

import com.example.cursosant.weather.domain.DataSource
import com.example.cursosant.weather.model.LocalDatabase
import com.example.cursosant.weather.model.RemoteDatabase
import com.example.cursosant.weather.viewmodel.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    single { RemoteDatabase(service = get(), get()) }
    single { LocalDatabase(get(), get(), get(), get()) }
    single { DataSource(get(), get(), get()) }
    viewModel { WeatherViewModel(get()) }
}