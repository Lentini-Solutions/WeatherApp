package com.example.cursosant.cities.di

import com.example.cursosant.cities.model.LocalDatabase
import com.example.cursosant.cities.viewmodel.CitiesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val citiesModule = module {
    single { LocalDatabase(get(), get()) }
    viewModel { CitiesViewModel(get(), get()) }
}