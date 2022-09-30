package com.example.powerapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayForecast(
    val date: String,
    val sunrise: String,
    val sunset: String,
    val temp: ForecastTemp,
    val pressure: String,
    val humidity: String,
) : Parcelable
