package com.theodo.myweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theodo.myweather.data.model.Weather
import com.theodo.myweather.ui.theme.MyweatherTheme
import com.theodo.myweather.utils.DateUtils.formatTime
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyweatherTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("My Weather !")
                            }
                        )
                    }
                ) { innerPadding ->
                    val weatherData = viewModel.weatherData.collectAsState()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Button(
                            onClick = {
                                // Log the weather data
                                if (weatherData.value.isNotEmpty()) {
                                    weatherData.value.forEach { weather ->
                                        Log.d(
                                            "WeatherData",
                                            "Time: ${weather.time}, Temp: ${weather.temperature}°F, " +
                                                    "Wind Speed: ${weather.windSpeed} km/h, Precipitation: ${weather.precipitationIntensity} mm"
                                        )
                                    }
                                } else {
                                    Log.d("WeatherData", "No weather data available")
                                }
                            },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Refresh")
                        }
                        WeatherList(
                            weatherData = weatherData.value,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun WeatherList(weatherData: List<Weather>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        LazyColumn {
            items(weatherData) { weather ->
                val formattedTime = formatTime(weather.time)
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier.padding(vertical = 8.dp),
                ) {
                    Text(
                        text = "Time: $formattedTime, Temp: ${weather.temperature}°F, Wind Speed: ${weather.windSpeed} km/h, Precipitation: ${weather.precipitationIntensity} mm",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}