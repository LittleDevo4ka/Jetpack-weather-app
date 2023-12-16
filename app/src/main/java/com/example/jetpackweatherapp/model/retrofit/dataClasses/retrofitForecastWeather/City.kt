package com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather

import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitCurrentWeather.Coord

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