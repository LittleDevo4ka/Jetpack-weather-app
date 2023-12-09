package com.example.jetpackweatherapp.model

import android.util.Log
import com.example.jetpackweatherapp.model.dataClass.currentWeather.CurrentWeather
import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeather
import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeatherItem
import com.example.jetpackweatherapp.model.dataClass.retrofit.WeatherAPIService
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Repository() {

    private val tag = "Repository"

    private val retrofit = WeatherAPIService()

    var currentWeather: CurrentWeather? = null
    var currentForecastWeatherList: ArrayList<ForecastWeatherItem> = arrayListOf()
    var forecastWeatherList: ArrayList<List<ForecastWeatherItem>> = arrayListOf()

    private val enLocale = Locale("en")
    private val curDateFormat = SimpleDateFormat("EEE, HH:mm", enLocale)
    private val forecastDateFormat = SimpleDateFormat("HH:mm", enLocale)

    private val dayDateFormat = SimpleDateFormat("dd", enLocale)

    private val date = Date()

    suspend fun updateWeather(): Int {

        val tempForecastListWithLists: MutableList<List<ForecastWeatherItem>> = mutableListOf()
        val tempForecastWeatherList: MutableList<ForecastWeatherItem> = mutableListOf()
        var prevDate = ""

        val currentWeatherResponse: Response<CurrentWeather>
        try {
            currentWeatherResponse = retrofit.getCurrentWeather().execute()
        } catch (e: Exception) {
            Log.w(tag, "getCurrentWeather: Something went really wrong: " + e.message)
            return 300
        }

        if (currentWeatherResponse.isSuccessful) {
            Log.w(tag, "getCurrentWeather: Everything all right: " + currentWeatherResponse.code())

            currentWeatherResponse.body()?.let {
                val tempDtLong = it.dt * 1000L
                it.dt_txt = dtToTxt(tempDtLong, curDateFormat)
                currentWeather = it


                val curWeatherDate = dtToTxt(tempDtLong, forecastDateFormat)
                prevDate = dtToTxt(tempDtLong, dayDateFormat)
                tempForecastWeatherList
                    .add(it.toForecastWeatherItem(curWeatherDate))
            }
        } else {
            Log.w(tag, "getCurrentWeather: Something went wrong: " + currentWeatherResponse.code())
            return 300
        }


        val forecastWeatherResponse: Response<ForecastWeather>
        try {
            forecastWeatherResponse = retrofit.getForecastWeather().execute()
        } catch (e: Exception) {
            Log.w(tag, "getForecastWeather: Something went really wrong: " + e.message)
            return 300
        }

        if (forecastWeatherResponse.isSuccessful) {
            Log.w(tag, "getForecastWeather: Everything all right: " + currentWeatherResponse.code())

            forecastWeatherResponse.body()?.let { tempForecastWeather ->

                tempForecastWeather.list.forEach {
                    val tempDtLong = it.dt * 1000L
                    it.dt_txt = dtToTxt(tempDtLong, forecastDateFormat)

                    val newDate = dtToTxt(tempDtLong, dayDateFormat)
                    if (newDate != prevDate) {
                        tempForecastListWithLists.add(tempForecastWeatherList.toList())
                        tempForecastWeatherList.clear()
                        tempForecastWeatherList.add(it)
                        prevDate = newDate
                    } else {
                        tempForecastWeatherList.add(it)
                    }
                }
                tempForecastListWithLists.add(tempForecastWeatherList.toList())

                currentForecastWeatherList = ArrayList(tempForecastListWithLists[0])
                tempForecastListWithLists.removeAt(0)
                forecastWeatherList = ArrayList(tempForecastListWithLists.toList())
            }
        } else {
            Log.w(tag, "getForecastWeather: Something went wrong: " + currentWeatherResponse.code())
            return 300
        }

        return 200
    }

    private fun getCurrentWeather() {

    }

    private fun getForecastWeather() {

    }

    private fun dtToTxt(dt: Long, tempFormat: SimpleDateFormat): String {
        date.time = dt
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