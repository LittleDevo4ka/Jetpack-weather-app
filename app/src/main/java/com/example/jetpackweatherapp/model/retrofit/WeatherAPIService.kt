package com.example.jetpackweatherapp.model.retrofit


import com.example.jetpackweatherapp.BuildConfig
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather.RetrofitForecastWeather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIService() {

    private val apiKey = BuildConfig.OpenWeatherMap_API_KEY
    private val baseURLcurWeather : String = "https://api.openweathermap.org/data/2.5/weather?"
    private val baseURLforWeather : String = "https://api.openweathermap.org/data/2.5/forecast?"
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getCurrentWeather(lat: Double = 0.0,
                          lon: Double = 0.0,
                          units: String = "metric"): Call<RetrofitCurrentWeather> {

        val urlCurWeather = "${baseURLcurWeather}lat=$lat&lon=$lon&appid=$apiKey&units=$units"

        return retrofit.getCurrentWeather(urlCurWeather)
    }


    fun getForecastWeather(lat: Double = 0.0,
                           lon: Double = 0.0,
                           units: String = "metric"): Call<RetrofitForecastWeather> {

        val urlForWeather = "${baseURLforWeather}lat=$lat&lon=$lon&appid=$apiKey&units=$units"

        return retrofit.getForecastWeather(urlForWeather)
    }
}