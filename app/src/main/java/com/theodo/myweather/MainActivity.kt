package com.theodo.myweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theodo.myweather.ui.theme.MyweatherTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
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
                                Text("My Weather")
                            }
                        )
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Weather(
    val time: String,
    val temperature: Double,
    val windSpeed: Double,
    val precipitationIntensity: Double
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {

        // Extended mock hourly weather data
        val weatherData = listOf(
            Weather(
                time = "2021-03-24T14:47:00-04:00",
                temperature = 56.98,
                windSpeed = 3.0,
                precipitationIntensity = 0.0083
            ),
            Weather(
                time = "2021-03-24T15:47:00-04:00",
                temperature = 57.92,
                windSpeed = 9.78,
                precipitationIntensity = 0.0161
            ),
            Weather(
                time = "2021-03-24T16:47:00-04:00",
                temperature = 55.29,
                windSpeed = 10.11,
                precipitationIntensity = 0.1362
            ),
            Weather(
                time = "2021-03-24T17:47:00-04:00",
                temperature = 54.67,
                windSpeed = 8.0,
                precipitationIntensity = 0.0502
            ),
            Weather(
                time = "2021-03-24T18:47:00-04:00",
                temperature = 53.12,
                windSpeed = 7.5,
                precipitationIntensity = 0.0109
            ),
            Weather(
                time = "2021-03-24T19:47:00-04:00",
                temperature = 52.33,
                windSpeed = 6.4,
                precipitationIntensity = 0.0223
            ),
            Weather(
                time = "2021-03-24T20:47:00-04:00",
                temperature = 51.50,
                windSpeed = 5.2,
                precipitationIntensity = 0.0056
            ),
            Weather(
                time = "2021-03-24T21:47:00-04:00",
                temperature = 50.22,
                windSpeed = 4.8,
                precipitationIntensity = 0.0121
            ),
            Weather(
                time = "2021-03-24T22:47:00-04:00",
                temperature = 49.11,
                windSpeed = 3.9,
                precipitationIntensity = 0.0094
            ),
            Weather(
                time = "2021-03-24T23:47:00-04:00",
                temperature = 48.00,
                windSpeed = 3.2,
                precipitationIntensity = 0.0023
            )
            // Add more hourly data as needed
        )

        // Display weather data in a LazyColumn
        LazyColumn {
            items(weatherData) { weather ->
                val formattedTime = formatTime(weather.time)
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
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

// Function to format the time from the provided timestamp
fun formatTime(time: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = inputFormat.parse(time)
    return outputFormat.format(date ?: Date())
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyweatherTheme {
        Greeting("Android")
    }
}