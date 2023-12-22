package com.example.jetpackweatherapp.model.retrofit.geoApify.dataClasses

data class RetrofitAutocompleteResponse(
    val query: Query?,
    val results: List<Result?>
)