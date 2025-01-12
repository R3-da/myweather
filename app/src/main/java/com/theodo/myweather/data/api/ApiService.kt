package com.theodo.myweather.data.api

import com.theodo.myweather.data.entity.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeatherHeadLine() : Response<WeatherResponse>

}