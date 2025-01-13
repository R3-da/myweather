@file:OptIn(ExperimentalFoundationApi::class)

package com.theodo.myweather.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.theodo.myweather.ui.composables.HeadingTextComposable
import com.theodo.myweather.ui.composables.Loader
import com.theodo.myweather.ui.composables.WeatherRowComposable
import com.theodo.myweather.viewmodel.WeatherViewModel
import com.theodo.myweather.utils.StateResource

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    WeatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherResp = WeatherViewModel.weather.collectAsState()
    val groupedWeatherData = WeatherViewModel.groupedWeatherData.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = weatherResp.value is StateResource.Loading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            // Trigger the refresh logic
            WeatherViewModel.refreshWeatherData()
        }
    ) {
        LazyColumn(
            modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ) {
            when (weatherResp.value) {
                is StateResource.Loading -> {
                    item {
                        Loader()
                    }
                }
                is StateResource.Success -> {
                    val response = (weatherResp.value as StateResource.Success).data
                    val groupedData = groupedWeatherData.value

                    if (groupedData.isNotEmpty()) {
                        // Iterate over each date and display the intervals grouped by date
                        groupedData.forEach { (date, intervals) ->
                            item {
                                HeadingTextComposable(
                                    textValue = date
                                )
                            }

                            items(intervals) { interval ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 8.dp, bottom = 8.dp),
                                    elevation = CardDefaults.cardElevation(2.dp)
                                ) {
                                    // Add internal padding here
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        WeatherRowComposable(interval = interval)
                                    }
                                }
                            }
                        }
                    } else {
                        item {
                            Text(text = "No grouped weather data available.")
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
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}