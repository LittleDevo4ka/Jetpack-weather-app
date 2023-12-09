package com.example.jetpackweatherapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackweatherapp.model.Repository
import com.example.jetpackweatherapp.model.dataClass.currentWeather.CurrentWeather
import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeatherItem
import com.example.jetpackweatherapp.viewModel.uiState.TodayUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class TodayViewModel(application: Application) : AndroidViewModel(application) {

    private val tag = "TodayViewModel"

    private val repository = Repository.getRepository()

    private val mutableUiState: MutableStateFlow<TodayUiState> =
        MutableStateFlow(TodayUiState())
    val todayUiState: StateFlow<TodayUiState> = mutableUiState.asStateFlow()

    init {
        if (repository.currentWeather == null) {
            updateWeather()
        }
    }

    fun updateWeather() {

        viewModelScope.launch(Dispatchers.IO) {

            val resultCode = repository.updateWeather()


            if (resultCode == 200) {
                mutableUiState.value =
                    mutableUiState.value.copy(
                        currentWeather = repository.currentWeather,
                        currentForecastWeatherList = repository.currentForecastWeatherList
                    )

            } else {
                mutableUiState.value =
                    mutableUiState.value.copy(requestResult = 300)
            }
        }
    }
}