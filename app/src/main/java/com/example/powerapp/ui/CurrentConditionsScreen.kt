package com.example.powerapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.example.powerapp.R
import com.example.powerapp.models.CurrentConditions

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentConditions(
    viewModel: CurrentConditionsViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onForecastButtonClick: () -> Unit,
) {
    val state by viewModel.currentConditions.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.app_name)) }
    ) {
        state?.let {
            CurrentConditionsContent(it) {
                onForecastButtonClick()
            }
        }
    }

}

@Composable
private fun CurrentConditionsContent(
    currentConditions: CurrentConditions,
    onForecastButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.city_name),
            style = TextStyle(
                fontWeight = FontWeight(600),
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.temperature, currentConditions.conditions.temperature.toInt()),
                    style = TextStyle(
                        fontWeight = FontWeight(400),
                        fontSize = 72.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.feels_like_temp, currentConditions.conditions.feelsLike.toInt()),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f, fill = true))
            val iconUrl = String.format("https://openweathermap.org/img/wn/%s@2x.png", currentConditions.weatherData.firstOrNull()?.iconName)
            AsyncImage(
                model = iconUrl,
                modifier = Modifier.size(72.dp),
                contentDescription = "Sunny"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            val textStyle = TextStyle(
                fontSize = 16.sp,
            )
            Text(text = stringResource(id = R.string.low_temp, currentConditions.conditions.minTemperature.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.high_temp, currentConditions.conditions.maxTemperature.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.humidity, currentConditions.conditions.humidity.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.pressure, currentConditions.conditions.pressure.toInt()), style = textStyle)
        }

        Spacer(modifier = Modifier.height(72.dp))
        Button(onClick = onForecastButtonClick) {
            Text(text = stringResource(id = R.string.forecast))
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun CurrentConditionsPreview() {
    CurrentConditions {}
}