package com.theodo.myweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.theodo.myweather.data.entity.WeatherResponse
import com.theodo.myweather.data.entity.Interval
import com.theodo.myweather.data.entity.Timeline
import com.theodo.myweather.data.entity.WeatherData
import com.theodo.myweather.data.entity.WeatherValues
import com.theodo.myweather.data.repository.WeatherRepo
import com.theodo.myweather.utils.StateResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import org.mockito.Mockito.mockStatic
import android.util.Log
import com.theodo.myweather.viewmodel.WeatherViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var weatherViewModel: WeatherViewModel

    @Mock
    private lateinit var weatherRepo: WeatherRepo

    @Before
    fun setUp() {
        // Initialize ViewModel with mocked repository
        weatherViewModel = WeatherViewModel(weatherRepo)
    }

    @Test
    fun testGroupDataByDate() {
        // Arrange: Prepare mock interval data
        val intervals = listOf(
            Interval(startTime = "2021-03-24T00:00:00Z", values = WeatherValues(
                precipitationIntensity = 0.0,
                precipitationType = null,
                windSpeed = 5.0,
                windGust = null,
                windDirection = null,
                temperature = 20.0,
                temperatureApparent = null,
                cloudCover = null,
                cloudBase = null,
                cloudCeiling = null,
                weatherCode = null
            )),
            Interval(startTime = "2021-03-24T01:00:00Z", values = WeatherValues(
                precipitationIntensity = 0.0,
                precipitationType = null,
                windSpeed = 5.5,
                windGust = null,
                windDirection = null,
                temperature = 21.0,
                temperatureApparent = null,
                cloudCover = null,
                cloudBase = null,
                cloudCeiling = null,
                weatherCode = null
            )),
            Interval(startTime = "2021-03-25T00:00:00Z", values = WeatherValues(
                precipitationIntensity = 0.0,
                precipitationType = null,
                windSpeed = 6.0,
                windGust = null,
                windDirection = null,
                temperature = 22.0,
                temperatureApparent = null,
                cloudCover = null,
                cloudBase = null,
                cloudCeiling = null,
                weatherCode = null
            ))
        )

        // Act: Group intervals by date
        val groupedData = weatherViewModel.groupDataByDate(intervals)

        // Assert: Verify the data is grouped correctly
        assertEquals(2, groupedData.size)
        assertTrue(groupedData.containsKey("2021-03-24"))
        assertTrue(groupedData.containsKey("2021-03-25"))
    }
}