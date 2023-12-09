package com.example.jetpackweatherapp.viewModel.uiState

import com.example.jetpackweatherapp.model.dataClass.currentWeather.CurrentWeather
import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeatherItem

data class TodayUiState(
    var requestResult: Int = 200,
    var currentWeather: CurrentWeather? = null,
    var currentForecastWeatherList: ArrayList<ForecastWeatherItem> = arrayListOf()
)
