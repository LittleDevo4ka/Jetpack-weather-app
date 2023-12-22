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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackweatherapp.R
import com.example.jetpackweatherapp.model.dataClasses.AutocompleteCity
import com.example.jetpackweatherapp.model.dataClasses.ForecastWeather
import com.example.jetpackweatherapp.model.dataClasses.MainWeatherInfo
import com.example.jetpackweatherapp.ui.theme.morningColor
import com.example.jetpackweatherapp.ui.theme.sunColor
import com.example.jetpackweatherapp.viewModel.ActivityViewModel
import com.example.jetpackweatherapp.viewModel.TodayViewModel

@Composable
fun TodayScreen(paddingValues: PaddingValues, activityViewModel: ActivityViewModel) {
    val todayViewModel: TodayViewModel = viewModel()
    val uiState = todayViewModel.todayUiState.collectAsState()
    val activityUiState = activityViewModel.uiState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

        LazyColumn(modifier = Modifier
            .fillMaxSize(),
            contentPadding = paddingValues){

            item {

                val citiesList = activityUiState.value.citiesList
                val textFieldContent = activityUiState.value.textFieldContent
                CityTextField(citiesList, textFieldContent, activityViewModel)

                val currentWeather = uiState.value.currentWeather

                currentWeather?.let {
                    CurrentWeatherCard(it, todayViewModel)
                    FutureWeatherCards(uiState.value.currentForecastWeatherList, todayViewModel)
                    SunriseSunsetCard(it, todayViewModel)
                    AdditionalInformationCard(it, todayViewModel)
                }
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun CityTextField(citiesList: ArrayList<AutocompleteCity>,
                          textFieldContext: String,
                          viewModel: ActivityViewModel) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val isExpanded = remember { mutableStateOf(false) }
    val isCancelled = remember { mutableStateOf(false) }

    if (isCancelled.value) {
        isExpanded.value = false
    } else {
        isExpanded.value = citiesList.isNotEmpty()
    }

    ExposedDropdownMenuBox(expanded = isExpanded.value,
        onExpandedChange = {},
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)) {

        TextField(modifier = Modifier
            .menuAnchor()
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp),
            value = textFieldContext,
            onValueChange = { viewModel.setTextFieldContent(it) },
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
                onSearch = {
                    isCancelled.value = false
                    viewModel.getCities(textFieldContext)
                }
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

        ExposedDropdownMenu(expanded = isExpanded.value,
            onDismissRequest = {
                isCancelled.value = true
                keyboardController?.hide() },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)) {

            citiesList.forEach { cityInfo ->

                DropdownMenuItem(
                    text = { Text(modifier = Modifier.fillMaxWidth(),
                        text = "City: ${cityInfo.city}, country: ${cityInfo.country}") },
                    onClick = {
                        viewModel.setTextFieldContent(cityInfo.city)
                        isCancelled.value = true
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
            }
        }
    }
}

@Composable
private fun CurrentWeatherCard(currentWeather: MainWeatherInfo, todayViewModel: TodayViewModel) {

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

                Text(text = currentWeather.cityName,
                    fontSize = 16.sp)


                Text(text = currentWeather.dtTxt,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(start = 16.dp))
            }

            Text(text = "${currentWeather.temp}°",
                fontSize = 64.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.padding(top = 8.dp, start = 20.dp)
            )

            Text(text = "Feels like: ${currentWeather.feelsLike}°",
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
private fun FutureWeatherCards(currentForecastWeatherList: ArrayList<ForecastWeather>,
                               todayViewModel: TodayViewModel) {
    LazyRow(modifier = Modifier
        .padding(top = 32.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)){
        items(currentForecastWeatherList.size) {
            WeatherListItem(currentForecastWeatherList[it])
        }
    }
}

@Composable
private fun SunriseSunsetCard(currentWeather: MainWeatherInfo,
                              todayViewModel: TodayViewModel) {
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

        Text(text = currentWeather.sunrise,
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

        Text(text = currentWeather.sunset,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(start = 4.dp))

    }
}

@Composable
private fun AdditionalInformationCard(
    currentWeather: MainWeatherInfo,
    todayViewModel: TodayViewModel
) {
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

            Text(text = "${currentWeather.pressure} hPa",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp))
        }

        Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
            Text(text = "Cloud cover:",
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal)

            Text(text = "${currentWeather.clouds}%",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp))
        }

        Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
            Text(text = "Visibility:",
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal)

            Text(text = "${currentWeather.visibility} m",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 16.dp))
        }
    }
}