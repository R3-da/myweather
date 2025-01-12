package com.theodo.myweather.data.entity

data class WeatherResponse(
    val data: WeatherData
)

data class WeatherData(
    val timelines: List<Timeline>
)

data class Timeline(
    val timestep: String,
    val startTime: String,
    val endTime: String,
    val intervals: List<Interval>
)

data class Interval(
    val startTime: String,
    val values: WeatherValues
)

data class WeatherValues(
    val precipitationIntensity: Double?,
    val precipitationType: Int?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windDirection: Double?,
    val temperature: Double?,
    val temperatureApparent: Double?,
    val cloudCover: Double?,
    val cloudBase: Double?,
    val cloudCeiling: Double?,
    val weatherCode: Int?
)