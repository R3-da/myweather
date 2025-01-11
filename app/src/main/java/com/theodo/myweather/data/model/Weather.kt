package com.theodo.myweather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    val time: String,
    val temperature: Double,
    val windSpeed: Double,
    val precipitationIntensity: Double,
    @PrimaryKey val id: Int? = null
)
