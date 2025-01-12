package com.theodo.myweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.theodo.myweather.data.entity.WeatherData
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
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp),
            color = Purple40
        )
    }
}

@Composable
fun NormalTextComponent(textValue: String) {
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
fun DescTextComponent(textValue: String) {
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
fun HeadingTextComponent(textValue: String) {
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
fun WeatherRowComponent(page: Int, interval: Interval) {
    val weatherValues = interval.values
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        HeadingTextComponent(textValue = interval.startTime)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                NormalTextComponent(textValue = "${weatherValues.temperature ?: "N/A"}°C")
            }
            Box(modifier = Modifier.weight(1f)) {
                NormalTextComponent(textValue = "${weatherValues.windSpeed ?: "N/A"} km/h")
            }
            Box(modifier = Modifier.weight(1f)) {
                DescTextComponent(textValue = "${weatherValues.temperatureApparent ?: "N/A"} °C")
            }
        }
    }
}

@Preview
@Composable
fun WeatherRowComponentPreview() {
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

    val timeline = Timeline(
        timestep = "1h",
        startTime = "2025-01-11T12:00:00Z",
        endTime = "2025-01-11T13:00:00Z",
        intervals = listOf(interval)
    )

    WeatherRowComponent(page = 0, interval = interval)
}