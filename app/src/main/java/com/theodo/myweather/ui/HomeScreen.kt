@file:OptIn(ExperimentalFoundationApi::class)

package com.theodo.myweather.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theodo.myweather.ui.components.Loader
import com.theodo.myweather.ui.components.WeatherRowComponent
import com.theodo.myweather.ui.viewmodel.WeatherViewModel
import com.theodo.myweather.utils.StateResource

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    WeatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherResp = WeatherViewModel.weather.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            // Button to refresh data
            Button(onClick = {
                when (val state = weatherResp.value) {
                    is StateResource.Loading -> {
                        Log.d(TAG, "Loading state: ${state}")
                    }
                    is StateResource.Success -> {
                        val response = state.data
                        Log.d(TAG, "Success state: $response")

                        // Filter timelines with timestep = "1h"
                        val timelines = response.data.timelines.filter { it.timestep == "1h" }
                        if (timelines.isNotEmpty()) {
                            timelines.forEach { timeline ->
                                Log.d(TAG, "Filtered timeline with timestep '1h': $timeline")
                                val intervals = timeline.intervals
                                if (intervals.isNotEmpty()) {
                                    intervals.forEach { interval ->
                                        Log.d(TAG, "Interval: $interval")
                                    }
                                }
                            }
                        } else {
                            Log.d(TAG, "No timelines with timestep '1h' found.")
                        }
                    }
                    is StateResource.Error -> {
                        val error = state.error
                        Log.d(TAG, "Error state: $error")
                    }
                }
            }) {
                Text(text = "Refresh")
            }
        }

        when (weatherResp.value) {
            is StateResource.Loading -> {
                item {
                    Loader()
                }
            }
            is StateResource.Success -> {
                val response = (weatherResp.value as StateResource.Success).data
                val timelines = response.data.timelines

                if (timelines.isNotEmpty()) {
                    // Display weather data for each timeline
                    items(timelines) { timeline ->
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            val interval = timeline.intervals.getOrNull(0) // For simplicity, use the first interval
                            WeatherRowComponent(timelines.indexOf(timeline), timeline)
                        }
                    }
                }
            }
            is StateResource.Error -> {
                item {
                    val error = (weatherResp.value as StateResource.Error)
                    Log.d(TAG, "Inside Error $error")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}