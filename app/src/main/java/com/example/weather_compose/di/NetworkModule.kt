package com.example.weather_compose.di

import com.example.weather_compose.api.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    @Named(value = "geocodingOkHttpClient")
//    fun provideOkHttpClient(): OkHttpClient {
//        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
//        return OkHttpClient.Builder()
//            .addInterceptor(logger)
//            .addInterceptor(Interceptor { chain ->
//                val newRequest: Request = chain.request().newBuilder()
//                    .addHeader("Authorization", "Bearer GoLHRTcnH9YL6zBVBf25GgNHlDhI")
//                    .build()
//                chain.proceed(newRequest)
//            })
//            .build()
//    }

    @Singleton
    @Provides
    @Named(value = "weatherOkHttpClient")
    fun provideweatherOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Singleton
    @Provides
    @Named(value = "weather")
    fun provideWeatherRetrofit(@Named("weatherOkHttpClient")client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://weather.visualcrossing.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Singleton
//    @Provides
//    @Named(value = "geocoding")
//    fun provideGeocodingRetrofit(@Named(value = "geocodingOkHttpClient") client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://test.api.amadeus.com/v1/reference-data/")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideGeocodingService(@Named(value = "geocoding") geocodingRetrofit: Retrofit): GeocodingService {
//        return geocodingRetrofit.create(GeocodingService::class.java)
//    }

    @Singleton
    @Provides
    fun provideWeatherService(@Named(value = "weather") weatherRetrofit: Retrofit): WeatherService {
        return weatherRetrofit.create(WeatherService::class.java)
    }
}
