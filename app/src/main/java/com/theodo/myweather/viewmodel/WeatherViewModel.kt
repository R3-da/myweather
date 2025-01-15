package com.theodo.myweather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theodo.myweather.data.entity.WeatherResponse
import com.theodo.myweather.data.entity.Interval
import com.theodo.myweather.data.repository.WeatherRepo
import com.theodo.myweather.utils.StateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) : ViewModel() {

    private val _weather: MutableStateFlow<StateResource<WeatherResponse>> =
        MutableStateFlow(StateResource.Loading())

    val weather: StateFlow<StateResource<WeatherResponse>> = _weather

    private val _groupedWeatherData: MutableStateFlow<Map<String, List<Interval>>> =
        MutableStateFlow(emptyMap())

    val groupedWeatherData: StateFlow<Map<String, List<Interval>>> = _groupedWeatherData

    init {
        getWeather()
    }

    fun refreshWeatherData() {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepo.getWeatherHeadline()
                .collectLatest { weatherResponse ->
                    _weather.value = weatherResponse

                    // Ensure the response is of type WeatherResponse and has a 'data' property
                    val weatherData = (weatherResponse as? StateResource.Success)?.data
                    Log.d("WeatherViewModel", "Weather Data: $weatherData")

                    weatherData?.let { response ->

                        // Assuming there is only one timeline, or you want to group data from all timelines
                        val intervals = response.data.timelines.flatMap { it.intervals }
                        // Group intervals by date
                        val groupedData = groupDataByDate(intervals)
                        _groupedWeatherData.value = groupedData
                    }
                }
        }
    }

    fun groupDataByDate(intervals: List<Interval>): Map<String, List<Interval>> {
        return intervals.groupBy { interval ->
            val startTime = interval.startTime
            val date = startTime.substring(0, 10) // Extract the date part (e.g., "2021-03-24")
            date
        }
    }
}