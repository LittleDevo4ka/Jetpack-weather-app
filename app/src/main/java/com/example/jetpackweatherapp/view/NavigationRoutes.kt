package com.example.jetpackweatherapp.view

sealed class NavigationRoutes(val route: String) {
    object TodayTabRoute: NavigationRoutes("todayTabScreen")
    object FutureTabRoute: NavigationRoutes("futureTabScreen")
}