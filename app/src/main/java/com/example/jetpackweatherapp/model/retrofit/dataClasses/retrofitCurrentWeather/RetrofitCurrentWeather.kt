package com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitCurrentWeather

import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather.ForecastWeatherItem

data class RetrofitCurrentWeather(
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
            com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather.Rain(
                0.0
            ), sys, newDtTxt)
    }
}