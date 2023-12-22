package com.example.jetpackweatherapp.model.retrofit.geoApify

import com.example.jetpackweatherapp.model.retrofit.geoApify.dataClasses.RetrofitAutocompleteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface AutocompleteAPI {

    //TODO(реализовать sealed классы для полей типо type, lang, format)

    @GET()
    fun getCities(
        @Url url : String,
        @Query("apiKey") apiKey: String,
        @Query("text") query: String,
        @Query("type") type: String,
        @Query("lang") lang: String,
        @Query("format") format: String
    ): Call<RetrofitAutocompleteResponse>
}