package com.example.weather_compose.api

import com.example.weather_compose.data.CityResponse
import com.example.weather_compose.models.City
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//interface GeocodingService {
//    @GET("locations/cities")
//    suspend fun getCities(
//        @Query("keyword") query: String
//    ): GeocodingResponse
//}