package com.example.cursosant.common.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cursosant.common.constants.Constants
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.Weather

@Database(entities = [Location::class, Weather::class], version = Constants.DB_INIT_VERSION)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDAO
    abstract fun weatherDao(): WeatherDao

    abstract fun WeatherCityDAO(): WeatherCityDAO
}