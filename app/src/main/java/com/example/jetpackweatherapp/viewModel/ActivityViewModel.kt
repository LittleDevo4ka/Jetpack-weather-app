package com.example.jetpackweatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackweatherapp.model.Repository
import com.example.jetpackweatherapp.view.BottomNavigationTab
import com.example.jetpackweatherapp.viewModel.uiState.ActivityUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository.getRepository()

    private val mutableSelectedTab: MutableStateFlow<Int> =
        MutableStateFlow(BottomNavigationTab.TodayTabRoute.tabIndex)
    val selectedTab: StateFlow<Int> = mutableSelectedTab.asStateFlow()

    private val mutableUiState: MutableStateFlow<ActivityUiState> =
        MutableStateFlow(ActivityUiState())
    val uiState: StateFlow<ActivityUiState> = mutableUiState.asStateFlow()

    fun setSelectedTab(newSelectedTab: Int) {
        mutableSelectedTab.value = newSelectedTab
    }

    fun getCities(query: String) {

        viewModelScope.launch(Dispatchers.IO) {

            mutableUiState.value =
                mutableUiState.value.copy(citiesList = arrayListOf())

            val resultCode = repository.getCities(query, "en")

            if (resultCode == 200) {
                mutableUiState.value =
                    mutableUiState.value.copy(
                        requestResult = resultCode,
                        citiesList = repository.autocompleteCities)
            } else {

                mutableUiState.value =
                    mutableUiState.value.copy(
                        requestResult = 300)
            }
        }
    }

    fun setTextFieldContent(content: String) {
        mutableUiState.value = mutableUiState.value.copy(textFieldContent = content)
    }

    fun clearCitiesList() {
        mutableUiState.value.citiesList.clear()
    }
}