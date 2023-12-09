package com.example.jetpackweatherapp.model.dataClass.forecastWeather

import com.example.jetpackweatherapp.model.dataClass.currentWeather.Clouds
import com.example.jetpackweatherapp.model.dataClass.currentWeather.Main
import com.example.jetpackweatherapp.model.dataClass.currentWeather.Sys
import com.example.jetpackweatherapp.model.dataClass.currentWeather.Weather
import com.example.jetpackweatherapp.model.dataClass.currentWeather.Wind
import java.text.SimpleDateFormat
import java.util.Calendar

data class ForecastWeatherItem(
    val dt : Int,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val rain : Rain,
    val sys: Sys,
    var dt_txt : String
)