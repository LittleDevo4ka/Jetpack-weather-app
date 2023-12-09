package com.example.jetpackweatherapp.model.dataClass.retrofit

import com.example.jetpackweatherapp.model.dataClass.currentWeather.CurrentWeather
import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherAPI {

    @GET
    fun getCurrentWeather(@Url url : String) : Call<CurrentWeather>

    @GET
    fun getForecastWeather(@Url url : String) : Call<ForecastWeather>
}