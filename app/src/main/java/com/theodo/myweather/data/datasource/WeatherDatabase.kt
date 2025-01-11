package com.theodo.myweather.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theodo.myweather.data.model.Weather

@Database(
    entities = [Weather::class],
    version = 1
)

abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}