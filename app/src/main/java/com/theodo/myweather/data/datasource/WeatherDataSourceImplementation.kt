package com.theodo.myweather.data.datasource

import com.theodo.myweather.data.api.ApiService
import com.theodo.myweather.data.entity.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherDataSourceImplementation @Inject constructor(
    private val apiService: ApiService
) : WeatherDataSource {

    override suspend fun getWeatherHeadLine(): Response<WeatherResponse> {
        return apiService.getWeatherHeadLine()
    }
}