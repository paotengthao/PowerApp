package com.example.powerapp.models

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "icon") val iconName: String,
)

data class CurrentConditionsData(
    @Json(name = "temp") val temperature: Float,
    @Json(name = "feels_like") val feelsLike: Float,
    @Json(name = "temp_min") val minTemperature: Float,
    @Json(name = "temp_max") val maxTemperature: Float,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Float,
)

data class CurrentConditions(
    @Json(name = "name") val cityName: String,
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "main") val conditions: CurrentConditionsData,
)

data class ForecastList(
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val forecastData: ForecastData,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Int,
)

data class ForecastData(
    @Json(name = "temp_min") val minTemperature: Float = 65f,
    @Json(name = "temp_max") val maxTemperature: Float = 80f,
)

data class ForecastsScreen(
    @Json(name = "list") val forecastList: List<ForecastList>,
)
