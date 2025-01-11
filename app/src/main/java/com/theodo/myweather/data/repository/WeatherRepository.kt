package com.theodo.myweather.data.repository

import com.theodo.myweather.data.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherData(): Flow<List<Weather>>
}