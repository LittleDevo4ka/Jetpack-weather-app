package com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitForecastWeather

data class RetrofitForecastWeather(
    val cod : String,
    val message : Int,
    val cnt : Int,
    val list: List<ForecastWeatherItem>,
    val city: City
)

