package com.example.powerapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastTemp(
    val day: String,
    val min: String,
    val max: String,
) : Parcelable
