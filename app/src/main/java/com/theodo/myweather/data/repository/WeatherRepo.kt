package com.theodo.myweather.data.repository

import com.theodo.myweather.data.datasource.WeatherDataSource
import com.theodo.myweather.data.entity.WeatherResponse
import com.theodo.myweather.utils.StateResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) {
    suspend fun getWeatherHeadline() : Flow<StateResource<WeatherResponse>> {
        return flow {
            emit(StateResource.Loading())
            val response = weatherDataSource.getWeatherHeadLine()

            if(response.isSuccessful && response.body() != null ){
                emit(StateResource.Success(response.body()!!))
            } else {
                emit(StateResource.Error("Error Fetching The Weather Data"))
            }
        }.catch { e->
            emit(StateResource.Error(e?.localizedMessage ?: "Some error in flow"))
        }
    }
}