package com.theodo.myweather.data.repository

import com.theodo.myweather.data.datasource.WeatherDao
import com.theodo.myweather.data.model.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override fun getWeatherData(): Flow<List<Weather>> {
        return weatherDao.getWeatherData()
    }
}