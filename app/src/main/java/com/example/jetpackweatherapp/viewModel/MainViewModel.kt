package com.example.jetpackweatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jetpackweatherapp.view.NavigationIntRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableSelectedTab: MutableStateFlow<Int> =
        MutableStateFlow(NavigationIntRoutes.TodayTabRoute.route)
    val selectedTab: StateFlow<Int> = mutableSelectedTab.asStateFlow()

    fun setSelectedTab(newSelectedTab: Int) {
        mutableSelectedTab.value = newSelectedTab
    }
}