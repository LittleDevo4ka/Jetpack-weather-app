package com.example.jetpackweatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    modifier = Modifier.fillMaxSize()
                ) { contentPadding ->
                    NavGraph(navController = navController, contentPadding)
                }
            }
        }
    }

    @Composable
    private fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
        NavHost(navController = navController, startDestination = "homeScreen") {
            composable("homeScreen") { HomeScreen(paddingValues) }
        }
    }

    @Composable
    private fun HomeScreen(paddingValues: PaddingValues) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 22.dp, start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primary)) {

                Column {

                    Row(modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 16.dp, start = 20.dp),
                        verticalAlignment = Alignment.Bottom) {

                        Text(text = "Prague",
                            fontSize = 16.sp)

                        Text(text = "Thu, 07:13",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Normal,
                            modifier = Modifier.padding(start = 16.dp))
                    }

                    Text(text = "10°",
                        fontSize = 64.sp,
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.padding(top = 8.dp, start = 20.dp)
                    )

                    Text(text = "Feels like: 12°",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp, start = 20.dp))
                }

                Icon(painter = painterResource(R.drawable.ic_sunny_24),
                    contentDescription = "Weather icon",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(top = 24.dp, end = 28.dp)
                        .align(Alignment.TopEnd),
                    tint = sunColor)

            }

            LazyRow(modifier = Modifier
                .padding(top = 32.dp, start = 16.dp)){
                items(10) {
                    WeatherListItem()
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(72.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = MaterialTheme.colorScheme.primary)) {

                Text(text = "Sunrise",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 20.dp))

                Icon(painter = painterResource(id = R.drawable.ic_sunny_24),
                    contentDescription = "Sunrise icon",
                    tint = sunColor,
                    modifier = Modifier.padding(start = 12.dp))

                Text(text = "04:19",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(start = 4.dp))

                Text(text = "Sunrise",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 40.dp))

                Icon(painter = painterResource(id = R.drawable.ic_sunny_24),
                    contentDescription = "Sunrise icon",
                    tint = sunColor,
                    modifier = Modifier.padding(start = 12.dp))

                Text(text = "04:19",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(start = 4.dp))

            }

            Column(modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp))
                .background(color = MaterialTheme.colorScheme.primary)) {

                Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
                    Text(text = "Atmospheric pressure:",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal)

                    Text(text = "1000 hPa",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp))
                }

                Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
                    Text(text = "Cloud cover:",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal)

                    Text(text = "100%",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp))
                }

                Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
                    Text(text = "Visibility:",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal)

                    Text(text = "10000 m",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp, bottom = 16.dp))
                }
            }
        }
    }

    @Composable
    private fun WeatherListItem() {
        Card(modifier = Modifier
            .size(width = 90.dp, height = 158.dp)
            .padding(end = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.Black
            )) {
            
            Text(text = "07:00",
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 8.dp))

            Icon(painter = painterResource(id = R.drawable.ic_sunny_24),
                contentDescription = "Weather icon",
                tint = sunColor,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .size(48.dp))

            Text(text = "10°",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(alignment = Alignment.CenterHorizontally))

            Row(modifier = Modifier
                .padding(top = 6.dp, end = 8.dp)
                .align(alignment = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically) {

                Icon(painter = painterResource(id = R.drawable.ic_humidity_24),
                    contentDescription = "Humidity icon",
                    tint = morningColor,
                    modifier = Modifier
                        .size(16.dp))

                Text(text = "60%",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal)
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

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        JetpackWeatherAppTheme {
        }
    }

}