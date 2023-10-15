package com.example.jetpackweatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackweatherapp.R
import com.example.jetpackweatherapp.ui.theme.*

class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            JetpackWeatherAppTheme(darkTheme = false) {
                ChangeSystemBarsTheme(isSystemInDarkTheme())
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { TabLayout() }
                ) { contentPadding ->
                    NavGraph(navController = navController, contentPadding)
                }
            }
        }
    }

    @Composable
    private fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
        NavHost(navController = navController, startDestination = NavigationRoutes.TodayTabRoute.route) {
            composable(NavigationRoutes.TodayTabRoute.route) { TodayScreen(paddingValues).CreateView() }
            composable(NavigationRoutes.FutureTabRoute.route) {}
        }
    }

    @Composable
    fun TabLayout() {
        var selectedTab by rememberSaveable { mutableIntStateOf(0) }
        println(selectedTab)
        val tabsTitlesArray = stringArrayResource(id = R.array.tabsNames)

        TabRow(selectedTabIndex = selectedTab,
            indicator = @Composable {
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(it[selectedTab]),
                    color = Color.Black) },
            modifier = Modifier
                .wrapContentHeight()
                .systemBarsPadding()) {

            tabsTitlesArray.forEachIndexed { index, title ->

                Tab(selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.height(56.dp)) {

                        val tabIcon = when(index) {
                            0 -> {
                                painterResource(id = R.drawable.ic_sunny_24)
                            }
                            1 -> {
                                painterResource(id = R.drawable.ic_sunny_24)
                            }

                            else -> {
                                painterResource(id = R.drawable.ic_sunny_24)
                            }
                        }

                        Icon(painter = tabIcon,
                            contentDescription = "Tab icon",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .align(alignment = Alignment.CenterVertically))

                        Text(text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    }
                }
            }
        }
    }

    @Composable
    private fun ChangeSystemBarsTheme(lightTheme: Boolean) {
        val barColor = MaterialTheme.colorScheme.background.toArgb()

        LaunchedEffect(lightTheme) {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(barColor, barColor),
                navigationBarStyle = SystemBarStyle.light(barColor, barColor)
            )
        }
        /*
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

         */
    }

}