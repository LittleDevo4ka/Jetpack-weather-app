package com.example.jetpackweatherapp.model.retrofit

import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather.RetrofitForecastWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherAPI {

    @GET
    fun getCurrentWeather(@Url url : String) : Call<RetrofitCurrentWeather>

    @GET
    fun getForecastWeather(@Url url : String) : Call<RetrofitForecastWeather>
}