package com.example.jetpackweatherapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackweatherapp.R
import com.example.jetpackweatherapp.model.dataClasses.ForecastWeather
import com.example.jetpackweatherapp.model.dataClasses.MainWeatherInfo
import com.example.jetpackweatherapp.ui.theme.morningColor
import com.example.jetpackweatherapp.ui.theme.sunColor
import com.example.jetpackweatherapp.viewModel.FutureViewModel

@Composable
fun FutureScreen(paddingValues: PaddingValues) {
    val futureViewModel: FutureViewModel = viewModel()
    val uiState = futureViewModel.futureUiState.collectAsState()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        contentPadding = paddingValues) {

        val forecastWeatherList = uiState.value.forecastWeatherList
        val forecastWeather = uiState.value.forecastWeather

        forecastWeather?.let {

            item {
                CityTextField()

                if (forecastWeather.isNotEmpty()) {
                    FutureDayWeatherCard(22.dp, forecastWeather[0], forecastWeatherList[0])
                }

                for (i in 1 until forecastWeather.size) {
                    FutureDayWeatherCard(
                        forecastWeather = forecastWeather[i],
                        forecastWeatherList = forecastWeatherList[i])
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CityTextField() {
    val textFieldStr = rememberSaveable{ mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        value = textFieldStr.value,
        onValueChange = { textFieldStr.value = it},
        shape = RoundedCornerShape(10.dp),
        placeholder = { Text(text = "Russia, Moscow", color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 16.sp)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { keyboardController?.hide() }
        ),
        colors = TextFieldDefaults
            .colors(focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                selectionColors = TextSelectionColors(handleColor = morningColor,
                    backgroundColor = morningColor)
            ))
}


@Composable
private fun FutureDayWeatherCard(topPadding: Dp = 16.dp,
                                 forecastWeather: MainWeatherInfo,
                                 forecastWeatherList: ArrayList<ForecastWeather>) {

    val showBottomSheet = rememberSaveable{ mutableStateOf(false) }

    if (showBottomSheet.value) {

        FutureBottomSheet(showBottomSheet, forecastWeather, forecastWeatherList)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = topPadding, start = 16.dp, end = 16.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(MaterialTheme.colorScheme.primary)
        .clickable {
            showBottomSheet.value = true
        }) {

        Column {

            Text(modifier = Modifier
                .wrapContentSize()
                .padding(top = 16.dp, start = 20.dp),
                text = forecastWeather.cityName,
                fontSize = 16.sp)

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