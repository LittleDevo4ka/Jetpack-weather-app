package com.example.jetpackweatherapp.model.dataClasses

import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitForecastWeather.ForecastWeatherItem
import java.text.SimpleDateFormat
import java.util.Date

data class ForecastWeather(
    val temp: Int,
    val humidity: Int,
    val dt: Int,
    val dtTxt: String
) {

    companion object {

        fun formForecastWeatherItem(
            forecastWeatherItem: ForecastWeatherItem,
            timeFormat: SimpleDateFormat,
            date: Date): ForecastWeather {

            val temp = forecastWeatherItem.main.temp.toInt()
            val humidity = forecastWeatherItem.main.humidity
            val dt = forecastWeatherItem.dt
            val dtTxt = dtToTxt(dt, timeFormat, date)

            return ForecastWeather(temp, humidity, dt, dtTxt)
        }

        fun fromRetrofitCurrentWeather(
            retrofitCurrentWeather: RetrofitCurrentWeather,
            timeFormat: SimpleDateFormat,
            date: Date): ForecastWeather {

            val temp = retrofitCurrentWeather.main.temp.toInt()
            val humidity = retrofitCurrentWeather.main.humidity
            val dt = retrofitCurrentWeather.dt
            val dtTxt = dtToTxt(dt, timeFormat, date)

            return ForecastWeather(temp, humidity, dt, dtTxt)
        }

        fun dtToTxt(dt: Int,
                    dateFormat: SimpleDateFormat,
                    date: Date): String {

            date.time = dt * 1000L
            return dateFormat.format(date)
        }
    }
}
