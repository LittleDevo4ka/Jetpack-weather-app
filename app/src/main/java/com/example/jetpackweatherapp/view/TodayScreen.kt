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
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackweatherapp.R
import com.example.jetpackweatherapp.ui.theme.grayColor59
import com.example.jetpackweatherapp.ui.theme.morningColor
import com.example.jetpackweatherapp.ui.theme.sunColor
import com.example.jetpackweatherapp.viewModel.MainViewModel

@Composable
fun TodayScreen(paddingValues: PaddingValues) {
    val mainViewModel: MainViewModel = viewModel()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

        LazyColumn(modifier = Modifier
            .fillMaxSize(),
            contentPadding = paddingValues){

            item {
                CityTextField()
                CurrentWeatherCard()
                FutureWeatherCards()
                SunriseSunsetCard()
                AdditionalInformationCard()
            }
        }

    }
}

@Composable
private fun CityTextField() {

    val textFieldStr = rememberSaveable{ mutableStateOf("") }

    TextField(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        value = textFieldStr.value,
        onValueChange = { textFieldStr.value = it},
        shape = RoundedCornerShape(10.dp),
        placeholder = { Text(text = "Russia, Moscow", color = grayColor59, fontSize = 16.sp)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults
            .colors(focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                selectionColors = TextSelectionColors(handleColor = morningColor,
                    backgroundColor = morningColor)
            ))
}

@Composable
private fun CurrentWeatherCard() {
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
private fun FutureWeatherCards() {
    LazyRow(modifier = Modifier
        .padding(top = 32.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)){
        items(10) {
            WeatherListItem()
        }
    }
}

@Composable
private fun SunriseSunsetCard() {
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

        Text(text = "04:19",
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

        Text(text = "04:19",
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(start = 4.dp))

    }
}

@Composable
private fun AdditionalInformationCard() {
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