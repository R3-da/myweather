package com.theodo.myweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theodo.myweather.data.repository.WeatherRepository
import com.theodo.myweather.data.model.Weather
import com.theodo.myweather.data.repository.WeatherRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: WeatherRepositoryImpl
): ViewModel() {
    val weatherData: StateFlow<List<Weather>> = repository.getWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
