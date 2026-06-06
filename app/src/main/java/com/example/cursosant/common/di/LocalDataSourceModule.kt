package com.example.cursosant.common.di

import android.app.Application
import androidx.room.Room
import com.example.cursosant.common.constants.Constants
import com.example.cursosant.common.model.AppDatabase
import com.example.cursosant.common.model.CityDAO
import com.example.cursosant.common.model.WeatherCityDAO
import com.example.cursosant.common.model.WeatherDao
import org.koin.dsl.module

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        context = application,
        klass = AppDatabase::class.java,
        name = Constants.DB_NAME
    )
        .build()
}

fun provideCityDao(database: AppDatabase): CityDAO = database.cityDao()
fun provideWeatherDao(database: AppDatabase): WeatherDao =database.weatherDao()

fun provideWeatherCityDao(database: AppDatabase): WeatherCityDAO =database.WeatherCityDAO()

val localDataSourceModule = module {
    single { provideCityDao(get()) }
    single { provideDatabase(get()) }
    single { provideWeatherDao(get()) }
    single { provideWeatherCityDao(get()) }
}