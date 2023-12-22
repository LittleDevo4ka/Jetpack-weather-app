package com.example.jetpackweatherapp.model.dataClasses

import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitForecastWeather.ForecastWeatherItem
import com.example.jetpackweatherapp.model.retrofit.openWeatherMap.dataClasses.retrofitForecastWeather.RetrofitForecastWeather
import java.text.SimpleDateFormat
import java.util.Date

data class MainWeatherInfo(
    val cityName: String,
    val cityLat: Double,
    val cityLon: Double,
    val dtTxt: String,
    val dt: Int,
    val temp: Int,
    val feelsLike: Int,
    val sunrise: String,
    val sunset: String,
    val pressure: Int,
    val clouds: Int,
    val visibility: Int
) {

    companion object {

        fun fromRetrofitCurrentWeather(
            retrofitCurrentWeather: RetrofitCurrentWeather,
            dateFormat: SimpleDateFormat,
            sunDateFormat: SimpleDateFormat,
            date: Date): MainWeatherInfo {

            val cityName = retrofitCurrentWeather.name
            val cityLat = retrofitCurrentWeather.coord.lat
            val cityLon = retrofitCurrentWeather.coord.lon
            val temp = retrofitCurrentWeather.main.temp.toInt()
            val feelsLike = retrofitCurrentWeather.main.feels_like.toInt()
            val dt = retrofitCurrentWeather.dt
            val dtTxt = dtToTxt(dt, dateFormat, date)
            val sunrise = dtToTxt(retrofitCurrentWeather.sys.sunrise, sunDateFormat, date)
            val sunset = dtToTxt(retrofitCurrentWeather.sys.sunset, sunDateFormat, date)
            val pressure = retrofitCurrentWeather.main.pressure
            val clouds = retrofitCurrentWeather.clouds.all
            val visibility = retrofitCurrentWeather.visibility

            return MainWeatherInfo(
                cityName, cityLat, cityLon,
                dtTxt, dt, temp,
                feelsLike, sunrise, sunset,
                pressure, clouds, visibility)
        }

        fun fromRetrofitForecastWeather(
            retrofitForecastWeather: RetrofitForecastWeather,
            forecastWeatherList: List<ForecastWeatherItem>,
            dateFormat: SimpleDateFormat,
            sunDateFormat: SimpleDateFormat,
            date: Date): MainWeatherInfo? {

            if (forecastWeatherList.isNotEmpty()) {
                val cityName = retrofitForecastWeather.city.name
                val cityLat = retrofitForecastWeather.city.coord.lat
                val cityLon = retrofitForecastWeather.city.coord.lon
                val dt = forecastWeatherList[0].dt
                val dtTxt = dtToTxt(dt, dateFormat, date)
                val sunrise = dtToTxt(forecastWeatherList[0].sys.sunrise, sunDateFormat, date)
                val sunset = dtToTxt(forecastWeatherList[0].sys.sunset, sunDateFormat, date)

                var temp = 0.0
                var feelsLike = 0.0
                var pressure = 0.0
                var clouds = 0.0
                var visibility = 0.0
                val listSize = forecastWeatherList.size
                forecastWeatherList.forEach { forecastWeatherItem ->
                    temp += forecastWeatherItem.main.temp
                    feelsLike += forecastWeatherItem.main.feels_like
                    pressure += forecastWeatherItem.main.pressure
                    clouds += forecastWeatherItem.clouds.all
                    visibility += forecastWeatherItem.visibility
                }

                temp /= listSize
                feelsLike /= listSize
                pressure /= listSize
                clouds /= listSize
                visibility /= listSize

                return MainWeatherInfo(
                    cityName, cityLat, cityLon,
                    dtTxt, dt, temp.toInt(),
                    feelsLike.toInt(), sunrise, sunset,
                    pressure.toInt(), clouds.toInt(), visibility.toInt())
            } else return null

        }


        fun dtToTxt(dt: Int,
                    dateFormat: SimpleDateFormat,
                    date: Date): String {

            date.time = dt * 1000L
            return dateFormat.format(date)
        }
    }
}
