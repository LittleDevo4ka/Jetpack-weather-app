package com.example.jetpackweatherapp.model.dataClasses

import com.example.jetpackweatherapp.model.retrofit.geoApify.dataClasses.Result

data class AutocompleteCity(
    val city: String,
    val cityLat: Double,
    val cityLon: Double,
    val country: String
) {

    companion object {

        fun fromAutocompleteFeature(result: Result): AutocompleteCity {

            val cityName = result.city ?: ""
            val cityLat = result.lat ?: 0.0
            val cityLon = result.lon ?: 0.0
            val countryName = result.country ?: ""

            return AutocompleteCity(cityName, cityLat, cityLon, countryName)
        }
    }
}
