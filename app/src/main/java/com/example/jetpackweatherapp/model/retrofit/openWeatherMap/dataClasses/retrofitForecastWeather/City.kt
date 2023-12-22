package com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitForecastWeather

import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitCurrentWeather.Coord

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)