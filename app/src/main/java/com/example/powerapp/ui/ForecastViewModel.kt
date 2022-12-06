package com.example.powerapp.ui

import androidx.lifecycle.ViewModel
import com.example.powerapp.models.ForecastsScreen
import com.example.powerapp.service.OpenWeatherMapApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val api: OpenWeatherMapApi): ViewModel(){
    private val _forecast = Channel<ForecastsScreen>()

    public val forecast: Flow<ForecastsScreen> = _forecast.receiveAsFlow()

    fun fetchData() = runBlocking {
        val forecast = api.getForecastScreen("55423")
        _forecast.trySend(forecast)
    }
}