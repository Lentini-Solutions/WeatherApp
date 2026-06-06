package com.example.cursosant.common.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cursosant.common.constants.Constants
import com.example.cursosant.common.entities.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDAO {

    @Insert
    suspend fun addCity(city: Location): Long

    @Update
    suspend fun updateCity(city: Location): Int

    @Delete
    suspend fun deleteCity(city: Location): Int

    @Query("SELECT * FROM ${Constants.E_CITY}")
    suspend fun getAllCities(): List<Location>

    @Query("SELECT * FROM ${Constants.E_CITY}")
    fun getRealTimeAllCities(): Flow<List<Location>>

    @Query("SELECT * FROM ${Constants.E_CITY} " +
            "WHERE ${Constants.P_NAME} = :name " +
            "AND ${Constants.P_COUNTRY} = :country LIMIT 1")
    suspend fun getCityByNameAndCountry(name: String, country: String): Location?

}