package com.example.jetpackweatherapp.view

import com.example.jetpackweatherapp.R

sealed class NavigationRoute(val route: String) {
    object TodayRoute: NavigationRoute("todayTabScreen")
    object FutureRoute: NavigationRoute("futureTabScreen")
}

sealed class BottomNavigationTab(val route: NavigationRoute, val tabIndex: Int,
                                 val icon: Int, val filledIcon: Int, val title: Int) {
    object TodayTabRoute: BottomNavigationTab(NavigationRoute.TodayRoute, 0,
        R.drawable.ic_sunny_24, R.drawable.ic_sunny_filled_24, R.string.TodayTabName)

    object FutureTabRoute: BottomNavigationTab(NavigationRoute.FutureRoute, 1,
        R.drawable.ic_date_range_24, R.drawable.ic_date_range_filled_24, R.string.FutureTabName)
}