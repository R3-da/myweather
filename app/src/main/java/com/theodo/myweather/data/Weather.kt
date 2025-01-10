package com.theodo.myweather.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
