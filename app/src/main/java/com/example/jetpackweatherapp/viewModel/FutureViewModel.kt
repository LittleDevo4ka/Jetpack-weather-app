package com.example.jetpackweatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackweatherapp.model.Repository
import com.example.jetpackweatherapp.viewModel.uiState.FutureUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FutureViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository.getRepository()

    private val mutableFutureUiState: MutableStateFlow<FutureUiState> =
        MutableStateFlow(FutureUiState())
    val futureUiState: StateFlow<FutureUiState> = mutableFutureUiState.asStateFlow()

    init {
        if (repository.forecastWeatherByDay == null) {
            updateWeather()
        } else {
            mutableFutureUiState.value =
                mutableFutureUiState.value.copy(
                    requestResult = 200,
                    forecastWeatherList = repository.forecastWeatherByDayByHour,
                    forecastWeather = repository.forecastWeatherByDay
                )
        }
    }

    private fun updateWeather() {
        viewModelScope.launch(Dispatchers.IO) {

            val resultCode = repository.updateWeather()


            if (resultCode == 200) {
                mutableFutureUiState.value =
                    mutableFutureUiState.value.copy(
                        requestResult = resultCode,
                        forecastWeatherList = repository.forecastWeatherByDayByHour,
                        forecastWeather = repository.forecastWeatherByDay
                    )

            } else {
                mutableFutureUiState.value =
                    mutableFutureUiState.value.copy(requestResult = 300)
            }
        }
    }

}