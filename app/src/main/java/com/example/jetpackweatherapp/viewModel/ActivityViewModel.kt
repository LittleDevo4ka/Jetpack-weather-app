package com.example.jetpackweatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jetpackweatherapp.view.BottomNavigationTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableSelectedTab: MutableStateFlow<Int> =
        MutableStateFlow(BottomNavigationTab.TodayTabRoute.tabIndex)
    val selectedTab: StateFlow<Int> = mutableSelectedTab.asStateFlow()

    fun setSelectedTab(newSelectedTab: Int) {
        mutableSelectedTab.value = newSelectedTab
    }
}