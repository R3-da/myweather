package com.theodo.myweather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey val time: String,
    val temperature: Double,
    val windSpeed: Double,
    val precipitationIntensity: Double
)

