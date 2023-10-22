package com.example.jetpackweatherapp.view

sealed class NavigationRoutes(val route: String) {
    object TodayTabRoute: NavigationRoutes("todayTabScreen")
    object FutureTabRoute: NavigationRoutes("futureTabScreen")
}

sealed class NavigationIntRoutes(val route: Int) {
    object TodayTabRoute: NavigationIntRoutes(0)
    object FutureTabRoute: NavigationIntRoutes(1)
}