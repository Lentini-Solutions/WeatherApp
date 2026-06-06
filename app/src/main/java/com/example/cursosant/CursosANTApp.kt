package com.example.cursosant

import android.app.Application
import com.example.cursosant.cities.di.citiesModule
import com.example.cursosant.common.di.componentsModule
import com.example.cursosant.common.di.localDataSourceModule
import com.example.cursosant.common.di.utilsModule
import com.example.cursosant.weather.di.remoteDataSourceModule
import com.example.cursosant.weather.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CursosANTApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CursosANTApp)
            modules(
                weatherModule, utilsModule,
                remoteDataSourceModule, localDataSourceModule,
                componentsModule, citiesModule
            )
        }
    }
}