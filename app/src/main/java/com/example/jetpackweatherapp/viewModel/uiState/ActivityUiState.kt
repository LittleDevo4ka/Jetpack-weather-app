package com.example.jetpackweatherapp.viewModel.uiState

import com.example.jetpackweatherapp.model.dataClasses.AutocompleteCity

data class ActivityUiState(
    val requestResult: Int = 200,
    var textFieldContent: String = "",
    var citiesList: ArrayList<AutocompleteCity> = arrayListOf()
)