package com.example.weather_compose.di

import android.content.Context
import com.example.weather_compose.data.CityRepository
import com.example.weather_compose.data.CityRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CityRepositoryModule {

    @Singleton
    @Provides
    fun provideCityRepository(
        @ApplicationContext context: Context
    ): CityRepository {
        return CityRepositoryImpl(context)
    }
}
