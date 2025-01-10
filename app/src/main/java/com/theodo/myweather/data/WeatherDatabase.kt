package com.theodo.myweather.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Weather::class],
    version = 1
)

abstract class WeatherDatabase: RoomDatabase() {
    abstract val dao: WeatherDao
}