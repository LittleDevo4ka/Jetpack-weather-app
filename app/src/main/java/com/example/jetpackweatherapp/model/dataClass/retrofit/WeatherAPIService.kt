package com.example.jetpackweatherapp.model.dataClass.retrofit


import com.example.jetpackweatherapp.BuildConfig
import com.example.jetpackweatherapp.model.Repository
import com.example.jetpackweatherapp.model.dataClass.currentWeather.CurrentWeather
import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeather
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
                          units: String = "metric"): Call<CurrentWeather> {

        val urlCurWeather = "${baseURLcurWeather}lat=$lat&lon=$lon&appid=$apiKey&units=$units"

        return retrofit.getCurrentWeather(urlCurWeather)
    }


    fun getForecastWeather(lat: Double = 0.0,
                           lon: Double = 0.0,
                           units: String = "metric"): Call<ForecastWeather> {

        val urlForWeather = "${baseURLforWeather}lat=$lat&lon=$lon&appid=$apiKey&units=$units"

        return retrofit.getForecastWeather(urlForWeather)
    }
}