package com.example.powerapp.service

import com.example.powerapp.models.CurrentConditions
import com.example.powerapp.models.ForecastsScreen
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "65fa8d2cb08ee51a7c1f31ffff2df47a",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecastScreen(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "65fa8d2cb08ee51a7c1f31ffff2df47a",
        @Query("cnt") cnt: Int = 16,
        @Query("units") units: String = "imperial"
    ) : ForecastsScreen
}