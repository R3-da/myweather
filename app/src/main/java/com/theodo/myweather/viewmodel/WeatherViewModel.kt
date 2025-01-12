package com.theodo.myweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theodo.myweather.data.entity.WeatherResponse
import com.theodo.myweather.data.repository.WeatherRepo
import com.theodo.myweather.utils.StateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
// Injecting the Hilt viewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) : ViewModel() {

    private val _weather: MutableStateFlow<StateResource<WeatherResponse>> =
        MutableStateFlow(StateResource.Loading())


    val weather: StateFlow<StateResource<WeatherResponse>> = _weather // Stateflow makes the value static
    init {
        getWeather()
    }

    fun refreshWeatherData() {
        getWeather()
    }

    fun getWeather(){
        // Background thread for the api call
        viewModelScope.launch (Dispatchers.IO){
            weatherRepo.getWeatherHeadline()
                .collectLatest { weatherResponse ->
                    _weather.value = weatherResponse
                }
        }
    }
}