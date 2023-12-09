package com.example.jetpackweatherapp.model.dataClass.forecastWeather

import com.example.jetpackweatherapp.model.dataClass.currentWeather.Coord

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