package com.example.powerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastAdapter(private val data: List<DayForecast>) : RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val forecastSunImage: ImageView
    private val forecastDate: TextView
    private val forecastTemp: TextView
    private val forecastHigh: TextView
    private val forecastLow: TextView
    private val forecastSunrise: TextView
    private val forecastSunset: TextView

    init {
        forecastSunImage = view.findViewById(R.id.forecast_current_icon)
        forecastDate = view.findViewById(R.id.forecast_date)
        forecastTemp = view.findViewById(R.id.forecast_temp)
        forecastHigh = view.findViewById(R.id.forecast_high)
        forecastLow = view.findViewById(R.id.forecast_low)
        forecastSunrise = view.findViewById(R.id.forecast_sunrise)
        forecastSunset = view.findViewById(R.id.forecast_sunset)
    }

    fun bind(data: DayForecast) {
        forecastDate.text = data.date
        forecastTemp.text = data.temp.day
        forecastHigh.text = data.temp.max
        forecastLow.text = data.temp.min
        forecastSunrise.text = data.sunrise
        forecastSunset.text = data.sunset
    }
}