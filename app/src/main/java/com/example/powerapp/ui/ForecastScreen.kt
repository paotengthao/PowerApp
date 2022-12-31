package com.example.powerapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.powerapp.R
import com.example.powerapp.models.*
import com.example.powerapp.toHourMinute
import com.example.powerapp.toMonthDay

val startDay = 1675152057L
val sunrise = 1675170000L
val sunset = 1675130400L

private val forecastListData = mutableListOf<ForecastList>()

val forecastData = (0 until 16).map {
    forecastListData.add(ForecastList(
        date = startDay + (it * (24 * 60 * 60)),
        sunrise = sunrise + (it * (24 * 60 * 60)),
        sunset = sunset + (it * (24 * 60 * 60)),
        forecastData = ForecastData(minTemperature = 65f + it, maxTemperature = 80f + it),
        pressure = 1024f,
        humidity = 76,
    ))
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForecastScreen(
    latitudeLongitude: LatitudeLongitude?,
    forecastViewModel: ForecastViewModel = hiltViewModel(),
) {
    val state by forecastViewModel.forecast.collectAsState(null)

    if (latitudeLongitude != null) {
        LaunchedEffect(Unit) {
            forecastViewModel.fetchForecastData(latitudeLongitude)
        }
    } else {
        LaunchedEffect(Unit) {
            forecastViewModel.fetchForecast()
        }
    }

    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.forecast)) }
    ) {
        state?.let {
            LazyColumn {
                items(items = forecastListData) { item: ForecastList ->
                    ForecastRow(forecasts = item)
                }
            }
        }
    }

}

@Composable
private fun ForecastRow(
    forecasts: ForecastList,
) {

    Row(
        modifier = Modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textStyle = TextStyle(
            fontSize = 12.sp,
        )
        Image(painter = painterResource(id = R.drawable.sun_icon), contentDescription = "")
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Text(
            text = forecasts.date.toMonthDay(),
            style = TextStyle(
                fontSize = 16.sp,
            )
        )
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column {
            Text(
                text = stringResource(id = R.string.high_temp, forecasts.forecastData.maxTemperature.toInt()),
                style = textStyle,
            )
            Text(
                text = stringResource(id = R.string.low_temp, forecasts.forecastData.minTemperature.toInt()),
                style = textStyle,
            )
        }
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = stringResource(id = R.string.sunrise, forecasts.sunrise.toHourMinute()),
                style = textStyle,
            )
            Text(
                text = stringResource(id = R.string.sunset, forecasts.sunset.toHourMinute()),
                style = textStyle,
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun ForecastRowPreview() {
    ForecastRow(forecasts = forecastListData[0])
}