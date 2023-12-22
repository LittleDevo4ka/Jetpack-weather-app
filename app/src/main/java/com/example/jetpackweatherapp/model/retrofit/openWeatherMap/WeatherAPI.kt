package com.example.jetpackweatherapp.model.retrofit.openWeatherMap

import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitForecastWeather.RetrofitForecastWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherAPI {

    //TODO(добавить querry параметры для lat, lon, units, appid)
    @GET
    fun getCurrentWeather(@Url url : String) : Call<RetrofitCurrentWeather>

    @GET
    fun getForecastWeather(@Url url : String) : Call<RetrofitForecastWeather>
}