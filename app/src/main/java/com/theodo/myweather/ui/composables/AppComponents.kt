package com.theodo.myweather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theodo.myweather.data.entity.Timeline
import com.theodo.myweather.data.entity.Interval
import com.theodo.myweather.data.entity.WeatherValues
import com.theodo.myweather.ui.theme.Purple40

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Align cards at the top
    ) {
        repeat(5) { // Display 5 placeholder cards
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 24.dp), // Match padding of real cards
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp) // Match internal padding of real cards
                ) {
                    // Placeholder for the start time
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .background(Color.Gray.copy(alpha = 0.2f))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Placeholder for temperature
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(20.dp)
                                .background(Color.Gray.copy(alpha = 0.2f))
                        )
                        // Placeholder for wind speed
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(20.dp)
                                .padding(start = 8.dp)
                                .background(Color.Gray.copy(alpha = 0.2f))
                        )
                        // Placeholder for apparent temperature
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(20.dp)
                                .padding(start = 8.dp)
                                .background(Color.Gray.copy(alpha = 0.2f))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NormalTextComposable(textValue: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            color = Purple40
        )
    )
}

@Composable
fun DescTextComposable(textValue: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            color = Color.Blue
        )
    )
}

@Composable
fun HeadingTextComposable(textValue: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    )
}

@Composable
fun WeatherRowComposable(interval: Interval) {
    val weatherValues = interval.values

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.weight(1f)) {
            HeadingTextComposable(textValue = interval.startTime.substring(
                interval.startTime.indexOf('T') + 1,  // Start after 'T'
                interval.startTime.indexOf(':', interval.startTime.indexOf(':') + 1)  // Find the second ':'
                )
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            NormalTextComposable(textValue = "${weatherValues.temperature ?: "N/A"}°C")
        }
        Box(modifier = Modifier.weight(1f)) {
            NormalTextComposable(textValue = "${weatherValues.windSpeed ?: "N/A"} km/h")
        }
        Box(modifier = Modifier.weight(1f)) {
            DescTextComposable(textValue = "${weatherValues.temperatureApparent ?: "N/A"} °C")
        }
    }

}

@Preview
@Composable
fun WeatherRowComposablePreview() {
    val weatherValues = WeatherValues(
        precipitationIntensity = 5.0,
        precipitationType = 1,
        windSpeed = 20.0,
        windGust = 30.0,
        windDirection = 180.0,
        temperature = 22.0,
        temperatureApparent = 21.0,
        cloudCover = 75.0,
        cloudBase = 1000.0,
        cloudCeiling = 1500.0,
        weatherCode = 3
    )

    val interval = Interval(
        startTime = "2025-01-11T12:00:00Z",
        values = weatherValues
    )

    // Pass only the interval, as the page parameter is no longer required
    WeatherRowComposable(interval = interval)
}