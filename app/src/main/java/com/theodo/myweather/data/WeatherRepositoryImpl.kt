package com.theodo.myweather.data

import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val dao: WeatherDao
): WeatherRepository {

    override suspend fun insertWeather(weather: Weather) {
        dao.insertTodo(weather)
    }

    override suspend fun deleteWeather(weather: Weather) {
        dao.deleteTodo(weather)
    }

    override suspend fun getWeatherById(id: Int): Weather? {
        return dao.getTodoById(id)
    }

    override fun getWeathers(): Flow<List<Weather>> {
        return dao.getTodos()
    }
}