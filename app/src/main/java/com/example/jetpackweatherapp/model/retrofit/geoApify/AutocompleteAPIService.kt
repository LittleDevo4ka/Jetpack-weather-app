package com.example.jetpackweatherapp.model.retrofit.geoApify

import com.example.jetpackweatherapp.BuildConfig
import com.example.jetpackweatherapp.model.retrofit.geoApify.dataClasses.RetrofitAutocompleteResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AutocompleteAPIService {

    private val apiKey: String = BuildConfig.Autocomplete_API_KEY
    private val baseUrlAutocomplete : String = "https://api.geoapify.com/v1/geocode/autocomplete?"
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.geoapify.com/v1/geocode/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AutocompleteAPI::class.java)

    fun getCities(query: String, lang: String): Call<RetrofitAutocompleteResponse> {

        return retrofit.getCities(baseUrlAutocomplete, apiKey, query,
            "city", "en", "json")
    }
}