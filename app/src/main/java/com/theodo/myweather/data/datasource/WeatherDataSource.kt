package com.theodo.myweather.data.datasource

import com.theodo.myweather.data.entity.WeatherResponse
import retrofit2.Response

interface WeatherDataSource {
    suspend fun getWeatherHeadLine() : Response<WeatherResponse>
}