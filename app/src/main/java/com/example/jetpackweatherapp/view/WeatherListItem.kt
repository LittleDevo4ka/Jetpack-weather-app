package com.example.jetpackweatherapp.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackweatherapp.R
import com.example.jetpackweatherapp.model.dataClass.forecastWeather.ForecastWeatherItem
import com.example.jetpackweatherapp.ui.theme.morningColor
import com.example.jetpackweatherapp.ui.theme.sunColor
import kotlin.math.round


@Composable
fun WeatherListItem(forecastWeatherItem: ForecastWeatherItem) {
    Card(modifier = Modifier
        .size(width = 90.dp, height = 158.dp)
        .padding(end = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )) {

        Text(text = forecastWeatherItem.dt_txt,
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 8.dp))

        Icon(painter = painterResource(id = R.drawable.ic_sunny_filled_24),
            contentDescription = "Weather icon",
            tint = sunColor,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .size(48.dp))

        Text(text = "${round(forecastWeatherItem.main.temp).toInt()}°",
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

            Text(text = "${forecastWeatherItem.main.humidity}%",
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal)
        }


    }
}