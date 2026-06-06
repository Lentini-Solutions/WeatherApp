package com.example.cursosant.common.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.cursosant.common.constants.Constants
import com.google.gson.annotations.SerializedName

@Entity(Constants.E_CITY)
data class Location(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String = "",
    var country: String = "",
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    @Ignore @SerializedName("tz_id") val tzId: String = ""
) {
    override fun toString(): String {
        return "$name ($country)"
    }
}
