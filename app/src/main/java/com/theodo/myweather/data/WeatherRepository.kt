package com.theodo.myweather.data

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun insertWeather(todo: Weather)

    suspend fun deleteWeather(todo: Weather)

    suspend fun getWeatherById(id: Int): Weather?

    fun getWeathers(): Flow<List<Weather>>
}