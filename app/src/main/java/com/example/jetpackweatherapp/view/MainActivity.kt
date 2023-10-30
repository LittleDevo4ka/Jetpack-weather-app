package com.example.jetpackweatherapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackweatherapp.ui.theme.JetpackWeatherAppTheme
import com.example.jetpackweatherapp.viewModel.MainViewModel

class MainActivity : ComponentActivity() {

    private var mainViewModel: MainViewModel? = null
    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackWeatherAppTheme() {
                ChangeSystemBarsTheme(!isSystemInDarkTheme())
                navController = rememberNavController()
                mainViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { TabLayout() }
                ) { contentPadding ->

                    NavGraph(contentPadding)

                    BackHandler {
                        customOnBackPressed()
                    }
                }
            }
        }
    }

    @Composable
    private fun NavGraph(paddingValues: PaddingValues) {
        navController?.let { tempNavController ->

            NavHost(navController = tempNavController,
                startDestination = NavigationRoute.TodayRoute.route) {

                composable(NavigationRoute.TodayRoute.route,
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) },
                    popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
                    popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }) {

                    TodayScreen(paddingValues)
                }

                composable(NavigationRoute.FutureRoute.route,
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) },
                    popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
                    popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }) {

                    FutureScreen(paddingValues)
                }
            }
        }
    }


    @Composable
    fun TabLayout() {
        val tabsArray: List<BottomNavigationTab> =
            listOf(BottomNavigationTab.TodayTabRoute, BottomNavigationTab.FutureTabRoute)

        val selectedTab = mainViewModel?.selectedTab?.collectAsState()?.value
            ?: BottomNavigationTab.TodayTabRoute.tabIndex

        TabRow(selectedTabIndex = selectedTab,
            indicator = @Composable {
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(it[selectedTab]),
                    color = MaterialTheme.colorScheme.onPrimary) },
            modifier = Modifier
                .wrapContentHeight()
                .systemBarsPadding()) {

            tabsArray.forEachIndexed { index, tabInfo ->

                Tab(selected = selectedTab == index,
                    onClick = { tabOnClick(index) },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.height(56.dp)) {

                        val tabIcon = if (index == selectedTab) {
                            painterResource(id = tabInfo.filledIcon)
                        } else {
                            painterResource(id = tabInfo.icon)
                        }


                        Icon(painter = tabIcon,
                            contentDescription = "Tab icon",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .align(alignment = Alignment.CenterVertically))

                        Text(text = getString(tabInfo.title),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    }
                }
            }
        }
    }

    private fun tabOnClick(tabIndex: Int) {

        when(tabIndex) {
            BottomNavigationTab.TodayTabRoute.tabIndex -> {
                if (navController?.currentDestination?.route != NavigationRoute.TodayRoute.route) {
                    mainViewModel?.setSelectedTab(BottomNavigationTab.TodayTabRoute.tabIndex)
                    navController?.navigate(NavigationRoute.TodayRoute.route)
                }

            }

            BottomNavigationTab.FutureTabRoute.tabIndex -> {

                if (navController?.currentDestination?.route != NavigationRoute.FutureRoute.route) {
                    mainViewModel?.setSelectedTab(BottomNavigationTab.FutureTabRoute.tabIndex)
                    navController?.navigate(NavigationRoute.FutureRoute.route)
                }
            }
        }
    }

    @Composable
    private fun ChangeSystemBarsTheme(lightTheme: Boolean) {
        val barColor = MaterialTheme.colorScheme.background.toArgb()

        LaunchedEffect(lightTheme) {

            if (lightTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        barColor, barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        barColor, barColor,
                    ),
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                )
            }
        }
    }

    private fun customOnBackPressed() {

        navController?.let {  tempNavController ->

            if (tempNavController.currentDestination?.route
                == NavigationRoute.TodayRoute.route) {
                finish()
            } else {
                mainViewModel?.setSelectedTab(BottomNavigationTab.TodayTabRoute.tabIndex)
                tempNavController.popBackStack(NavigationRoute.FutureRoute.route,
                    true)
            }
        }
    }
}