package com.example.powerapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.powerapp.databinding.FragmentForecastBinding
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    private lateinit var binding: FragmentForecastBinding
    private val args: ForecastFragmentArgs by navArgs()

    private val forecastData = listOf<DayForecast>(
        DayForecast("Jan 31", "Sunrise: 8:00am", "Sunset: 9:00pm",
            temp = ForecastTemp("Temp: 72°", "Low: 65°", "High: 80°"),
            "1023 hPa", "100%"),
        DayForecast("Feb 19", "Sunrise: 8:50am", "Sunset: 9:50pm",
            temp = ForecastTemp("Temp: 77°", "Low: 68°", "High: 82°"),
            "1023 hPa", "100%"),
        DayForecast("Feb 26", "Sunrise: 7:00am", "Sunset: 8:00pm",
            temp = ForecastTemp("Temp: 80°", "Low: 70°", "High: 85°"),
            "1023 hPa", "100%"),
        DayForecast("Mar 7", "Sunrise: 9:00am", "Sunset: 10:00pm",
            temp = ForecastTemp("Temp: 82°", "Low: 66°", "High: 88°"),
            "1023 hPa", "100%"),
        DayForecast("Mar 22", "Sunrise: 8:30am", "Sunset: 9:30pm",
            temp = ForecastTemp("Temp: 90°", "Low: 72°", "High: 93°"),
            "1023 hPa", "100%"),
        DayForecast("Apr 25", "Sunrise: 7:30am", "Sunset: 8:30pm",
            temp = ForecastTemp("Temp: 73°", "Low: 62°", "High: 78°"),
            "1023 hPa", "100%"),
        DayForecast("May 5", "Sunrise: 8:40am", "Sunset: 9:40pm",
            temp = ForecastTemp("Temp: 85°", "Low: 71°", "High: 90°"),
            "1023 hPa", "100%"),
        DayForecast("May 18", "Sunrise: 5:00am", "Sunset: 6:00pm",
            temp = ForecastTemp("Temp: 92°", "Low: 75°", "High: 96°"),
            "1023 hPa", "100%"),
        DayForecast("May 21", "Sunrise: 6:00am", "Sunset: 7:00pm",
            temp = ForecastTemp("Temp: 76°", "Low: 69°", "High: 81°"),
            "1023 hPa", "100%"),
        DayForecast("Jun 8", "Sunrise: 9:30am", "Sunset: 9:30pm",
            temp = ForecastTemp("Temp: 78°", "Low: 63°", "High: 84°"),
            "1023 hPa", "100%"),
        DayForecast("Jun 27", "Sunrise: 8:20am", "Sunset: 9:20pm",
            temp = ForecastTemp("Temp: 96°", "Low: 76°", "High: 98°"),
            "1023 hPa", "100%"),
        DayForecast("Jul 4", "Sunrise: 7:20am", "Sunset: 9:20pm",
            temp = ForecastTemp("Temp: 87°", "Low: 67°", "High: 92°"),
            "1023 hPa", "100%"),
        DayForecast("Jul 16", "Sunrise: 6:30am", "Sunset: 7:30pm",
            temp = ForecastTemp("Temp: 98°", "Low: 86°", "High: 100°"),
            "1023 hPa", "100%"),
        DayForecast("Jul 30", "Sunrise: 5:30am", "Sunset: 6:30pm",
            temp = ForecastTemp("Temp: 74°", "Low: 61°", "High: 83°"),
            "1023 hPa", "100%"),
        DayForecast("Aug 29", "Sunrise: 9:20am", "Sunset: 10:20pm",
            temp = ForecastTemp("Temp: 79°", "Low: 73°", "High: 86°"),
            "1023 hPa", "100%"),
        DayForecast("Sep 28", "Sunrise: 8:10am", "Sunset: 9:10pm",
            temp = ForecastTemp("Temp: 88°", "Low: 78°", "High: 95°"),
            "1023 hPa", "100%"),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)
        binding.forecastList.adapter = ForecastAdapter(forecastData)

        requireActivity().title = "Forecast"

        val dateTimeStamp = 1664388180L //"Sep 28"
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = LocalDateTime.ofEpochSecond(dateTimeStamp, 0,
            ZoneOffset.of("-5"))
        val formattedDate = formatter.format(dateTime)

        val timeFormatter = DateTimeFormatter.ofPattern("h:mm")
        val formattedTime = timeFormatter.format(dateTime)

        Log.d("ForecastFragment", formattedDate)
        Log.d("ForecastFragment", formattedTime)
    }
}