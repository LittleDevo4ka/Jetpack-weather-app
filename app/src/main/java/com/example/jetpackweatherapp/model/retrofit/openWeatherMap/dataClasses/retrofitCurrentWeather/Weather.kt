package com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitCurrentWeather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)