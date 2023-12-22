package com.example.jetpackweatherapp.viewModel.uiState

import com.example.jetpackweatherapp.model.dataClasses.AutocompleteCity
import com.example.jetpackweatherapp.model.dataClasses.ForecastWeather
import com.example.jetpackweatherapp.model.dataClasses.MainWeatherInfo
import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitForecastWeather.ForecastWeatherItem

data class TodayUiState(
    var requestResult: Int = 200,
    var currentWeather: MainWeatherInfo? = null,
    var currentForecastWeatherList: ArrayList<ForecastWeather> = arrayListOf(),
)
