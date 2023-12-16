package com.example.jetpackweatherapp.model

import android.util.Log
import com.example.jetpackweatherapp.model.dataClasses.ForecastWeather
import com.example.jetpackweatherapp.model.dataClasses.MainWeatherInfo
import com.example.jetpackweatherapp.model.retrofit.WeatherAPIService
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitCurrentWeather.RetrofitCurrentWeather
import com.example.jetpackweatherapp.model.retrofit.dataClasses.retrofitForecastWeather.RetrofitForecastWeather
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Repository() {

    private val tag = "Repository"

    private val retrofit = WeatherAPIService()

    var currentWeather: MainWeatherInfo? = null
    var currentWeatherByHour: ArrayList<ForecastWeather> = arrayListOf()

    var forecastWeatherByDay: ArrayList<MainWeatherInfo>? = null
    var forecastWeatherByDayByHour: ArrayList<ArrayList<ForecastWeather>> = arrayListOf()

    private val enLocale = Locale("en")

    private val mainDateFormat = SimpleDateFormat("EEE, HH:mm", enLocale)
    private val dayNameDateFormat = SimpleDateFormat("EEEE", enLocale)
    private val dayDateFormat = SimpleDateFormat("dd", enLocale)
    private val timeDateFormat = SimpleDateFormat("HH:mm", enLocale)

    private val date = Date()

    suspend fun updateWeather(): Int {


        val weatherByHour: MutableList<ForecastWeather> = mutableListOf()
        val weatherByDayByHour: MutableList<ArrayList<ForecastWeather>> = mutableListOf()
        val weatherByDay: MutableList<MainWeatherInfo> = mutableListOf()

        val retrofitCurrentWeather = getCurrentWeather() ?: return 300

        currentWeather = MainWeatherInfo
            .fromRetrofitCurrentWeather(retrofitCurrentWeather, mainDateFormat,
                timeDateFormat, date)

        weatherByHour.add(ForecastWeather
            .fromRetrofitCurrentWeather(retrofitCurrentWeather, timeDateFormat, date))

        var prevDate = dtToTxt(retrofitCurrentWeather.dt, dayDateFormat)

        val retrofitForecastWeather = getForecastWeather() ?: return 300

        var i = 0
        while (i < retrofitForecastWeather.list.size) {
            val forecastWeatherItem = retrofitForecastWeather.list[i]

            val newDate = dtToTxt(forecastWeatherItem.dt, dayDateFormat)
            if (newDate != prevDate) {

                break
            } else {
                weatherByHour.add(ForecastWeather
                    .formForecastWeatherItem(forecastWeatherItem, timeDateFormat, date))
                i++
            }
        }

        if (weatherByHour.size >= 2) {
            if (weatherByHour[0].dt > weatherByHour[1].dt) {
                weatherByHour.removeAt(1)
            }
        }
        currentWeatherByHour = ArrayList(weatherByHour)
        weatherByHour.clear()

        var prevDateIndex = currentWeatherByHour.size
        while (i < retrofitForecastWeather.list.size) {

            val forecastWeatherItem = retrofitForecastWeather.list[i]
            val newDate = dtToTxt(forecastWeatherItem.dt, dayDateFormat)

            if (newDate != prevDate) {

                weatherByDayByHour.add(ArrayList(weatherByHour))
                weatherByHour.clear()

                val forecastWeatherItemList = retrofitForecastWeather.list.subList(prevDateIndex, i+1)
                val tempMainWeatherInfo = MainWeatherInfo
                    .fromRetrofitForecastWeather(
                        retrofitForecastWeather, forecastWeatherItemList,
                        dayNameDateFormat, timeDateFormat, date)
                tempMainWeatherInfo?.let { weatherByDay.add(it) }

                prevDate = newDate
                prevDateIndex = i+1
                i++
            } else {
                weatherByHour.add(ForecastWeather
                    .formForecastWeatherItem(forecastWeatherItem, timeDateFormat, date))
                i++
            }
        }

        weatherByDayByHour.removeAt(0)
        weatherByDayByHour.add(ArrayList(weatherByHour))
        weatherByHour.clear()

        val forecastWeatherItemList = retrofitForecastWeather.list
            .subList(prevDateIndex, retrofitForecastWeather.list.size)
        val tempMainWeatherInfo = MainWeatherInfo
            .fromRetrofitForecastWeather(
                retrofitForecastWeather, forecastWeatherItemList,
                dayNameDateFormat, timeDateFormat, date)
        tempMainWeatherInfo?.let { weatherByDay.add(tempMainWeatherInfo) }

        forecastWeatherByDay = ArrayList(weatherByDay)
        forecastWeatherByDayByHour = ArrayList(weatherByDayByHour)

        return 200
    }

    private suspend fun getCurrentWeather(): RetrofitCurrentWeather? {

        val currentWeatherResponse: Response<RetrofitCurrentWeather>
        try {
            currentWeatherResponse = retrofit.getCurrentWeather().execute()
        } catch (e: Exception) {
            Log.w(tag, "getCurrentWeather: Something went really wrong: " + e.message)
            return null
        }

        return currentWeatherResponse.body()
    }

    private suspend fun getForecastWeather(): RetrofitForecastWeather? {

        val forecastWeatherResponse: Response<RetrofitForecastWeather>
        try {
            forecastWeatherResponse = retrofit.getForecastWeather().execute()
        } catch (e: Exception) {
            Log.w(tag, "getForecastWeather: Something went really wrong: " + e.message)
            return null
        }

        return forecastWeatherResponse.body()
    }

    private fun dtToTxt(dt: Int, tempFormat: SimpleDateFormat): String {
        date.time = dt * 1000L
        return tempFormat.format(date)
    }



    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getRepository(): Repository {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Repository()
                INSTANCE = instance
                return instance
            }
        }
    }
}