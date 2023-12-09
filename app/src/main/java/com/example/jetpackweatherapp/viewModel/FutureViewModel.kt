package com.example.jetpackweatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jetpackweatherapp.model.Repository
import com.example.jetpackweatherapp.viewModel.uiState.FutureUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FutureViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository.getRepository()

    private val mutableFutureUiState: MutableStateFlow<FutureUiState> =
        MutableStateFlow(FutureUiState())
    val futureUiState: StateFlow<FutureUiState> = mutableFutureUiState.asStateFlow()

}