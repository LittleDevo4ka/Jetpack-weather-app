package com.example.jetpackweatherapp.viewModel.uiState

import com.example.jetpackweatherapp.model.dataClasses.ForecastWeather
import com.example.jetpackweatherapp.model.dataClasses.MainWeatherInfo
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather.ForecastWeatherItem

data class TodayUiState(
    var requestResult: Int = 200,
    var currentWeather: MainWeatherInfo? = null,
    var currentForecastWeatherList: ArrayList<ForecastWeather> = arrayListOf()
)
