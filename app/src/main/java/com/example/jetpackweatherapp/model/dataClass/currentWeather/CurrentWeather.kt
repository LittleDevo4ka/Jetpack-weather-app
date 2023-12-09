package com.example.jetpackweatherapp.model.dataClass.currentWeather

import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeatherItem
import java.text.SimpleDateFormat
import java.util.Calendar

data class CurrentWeather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Rain,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
    var dt_txt: String = ""
) {

    fun toForecastWeatherItem(newDtTxt: String): ForecastWeatherItem {

        return ForecastWeatherItem(dt, main, weather, clouds, wind, visibility, pop = -1.0,
            com.example.jetpackweatherapp.model.dataClass.forecastWeather.Rain(0.0), sys, newDtTxt)
    }
}