package com.example.powerapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.powerapp.databinding.FragmentCurrentConditionsBinding

class CurrentConditionsFragment : Fragment(R.layout.fragment_current_conditions) {

    private lateinit var binding: FragmentCurrentConditionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "My Power App"

        binding = FragmentCurrentConditionsBinding.bind(view)
        binding.forecastButton.setOnClickListener {
            val forecast = DayForecast("Jan 31", "Sunrise: 8:00am", "Sunset: 9:00pm",
                temp = ForecastTemp("Temp: 72°", "Low: 65°", "High: 80°"),
                "1023 hPa", "100%")
            val action = CurrentConditionsFragmentDirections.actionCurrentConditionsFragmentToForecastFragment(forecast)
            findNavController().navigate(action)
        }
    }
}