package com.example.jetpackweatherapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackweatherapp.viewModel.MainViewModel

@Composable
fun FutureScreen(paddingValues: PaddingValues) {
    val mainViewModel: MainViewModel = viewModel()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .background(MaterialTheme.colorScheme.background)) {

    }
}