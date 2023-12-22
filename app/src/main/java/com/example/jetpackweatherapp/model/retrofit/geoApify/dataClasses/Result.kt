package com.example.jetpackweatherapp.model.retrofit.geoApify.dataClasses

data class Result(
    val address_line1: String,
    val address_line2: String,
    val bbox: Bbox,
    val category: String,
    val city: String?,
    val country: String?,
    val country_code: String,
    val county: String,
    val datasource: Datasource,
    val formatted: String,
    val lat: Double?,
    val lon: Double?,
    val municipality: String,
    val name: String,
    val place_id: String,
    val plus_code: String,
    val plus_code_short: String,
    val postcode: String,
    val rank: Rank,
    val region: String,
    val result_type: String,
    val state: String,
    val state_code: String,
    val timezone: Timezone
)