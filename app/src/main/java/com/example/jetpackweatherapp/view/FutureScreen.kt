package com.example.jetpackweatherapp.view

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackweatherapp.R
import com.example.jetpackweatherapp.ui.theme.grayColor59
import com.example.jetpackweatherapp.ui.theme.morningColor
import com.example.jetpackweatherapp.ui.theme.sunColor
import com.example.jetpackweatherapp.viewModel.MainViewModel

@Composable
fun FutureScreen(paddingValues: PaddingValues) {
    val mainViewModel: MainViewModel = viewModel()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        contentPadding = paddingValues) {

        item {
            CityTextField()
            FutureDayWeatherCard(22.dp)
        }

        items(4) {
            FutureDayWeatherCard()
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
private fun FutureDayWeatherCard(topPadding: Dp = 16.dp) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = topPadding, start = 16.dp, end = 16.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(MaterialTheme.colorScheme.primary)) {

        Column {

            Text(modifier = Modifier
                .wrapContentSize()
                .padding(top = 16.dp, start = 20.dp),
                text = "Thursday",
                fontSize = 16.sp)

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