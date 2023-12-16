package com.example.jetpackweatherapp.view

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackweatherapp.R
import com.example.jetpackweatherapp.model.dataClasses.ForecastWeather
import com.example.jetpackweatherapp.model.dataClasses.MainWeatherInfo
import com.example.jetpackweatherapp.ui.theme.sunColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FutureBottomSheet(showBottomSheet: MutableState<Boolean>,
                      forecastWeather: MainWeatherInfo,
                      forecastWeatherList: ArrayList<ForecastWeather>) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet.value = false
        },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background
    ) {

        LazyColumn(modifier = Modifier
            .fillMaxSize()){

            item {
                BottomSheetWeatherCard(forecastWeather)
                BottomSheetWeatherCards(forecastWeatherList)
                BottomSheetSunriseSunsetCard(forecastWeather)
                BottomSheetAdditionalInformationCard(forecastWeather)
            }
        }
    }
}

@Composable
private fun BottomSheetWeatherCard(forecastWeather: MainWeatherInfo) {

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

                Text(text = forecastWeather.cityName,
                    fontSize = 16.sp)

                Text(text = forecastWeather.dtTxt,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(start = 16.dp))
            }

            Text(text = "${forecastWeather.temp}°",
                fontSize = 64.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.padding(top = 8.dp, start = 20.dp)
            )

            Text(text = "Feels like: ${forecastWeather.feelsLike}°",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp, start = 20.dp))
        }

        Icon(painter = painterResource(R.drawable.ic_sunny_filled_24),
            contentDescription = "Weather icon",
            modifier = Modifier
                .size(120.dp)
                .padding(top = 24.dp, end = 28.dp)
                .align(Alignment.TopEnd),
            tint = sunColor
        )

    }
}

@Composable
private fun BottomSheetWeatherCards(forecastWeather: ArrayList<ForecastWeather>) {
    LazyRow(modifier = Modifier
        .padding(top = 32.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ){

        items(forecastWeather.size) {
            WeatherListItem(forecastWeather[it])
        }
    }
}

@Composable
private fun BottomSheetSunriseSunsetCard(forecastWeather: MainWeatherInfo) {

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

        Icon(painter = painterResource(id = R.drawable.ic_sunny_filled_24),
            contentDescription = "Sunrise icon",
            tint = sunColor,
            modifier = Modifier.padding(start = 12.dp))

        Text(text = forecastWeather.sunrise,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(start = 4.dp))

        Text(text = "Sunrise",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 40.dp))

        Icon(painter = painterResource(id = R.drawable.ic_sunny_filled_24),
            contentDescription = "Sunrise icon",
            tint = sunColor,
            modifier = Modifier.padding(start = 12.dp))

        Text(text = forecastWeather.sunset,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(start = 4.dp))

    }
}

@Composable
private fun BottomSheetAdditionalInformationCard(forecastWeather: MainWeatherInfo) {
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

            Text(text = "${forecastWeather.pressure} hPa",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp))
        }

        Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
            Text(text = "Cloud cover:",
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal)

            Text(text = "${forecastWeather.clouds}%",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp))
        }

        Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
            Text(text = "Visibility:",
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal)

            Text(text = "${forecastWeather.visibility} m",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 16.dp))
        }
    }
}