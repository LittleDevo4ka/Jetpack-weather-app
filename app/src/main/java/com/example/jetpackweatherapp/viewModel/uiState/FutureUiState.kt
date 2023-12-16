package com.example.jetpackweatherapp.viewModel.uiState

import com.example.jetpackweatherapp.model.dataClasses.ForecastWeather
import com.example.jetpackweatherapp.model.dataClasses.MainWeatherInfo
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather.ForecastWeatherItem

data class FutureUiState(
    val requestResult: Int = 200,
    val forecastWeather: ArrayList<MainWeatherInfo>? = null,
    val forecastWeatherList: ArrayList<ArrayList<ForecastWeather>> = arrayListOf()
)
