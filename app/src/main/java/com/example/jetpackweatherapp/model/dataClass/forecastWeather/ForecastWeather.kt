package com.example.jetpackweatherapp.model.dataClass.forecastWeather

data class ForecastWeather(
    val cod : String,
    val message : Int,
    val cnt : Int,
    val list: List<ForecastWeatherItem>,
    val city: City
)

