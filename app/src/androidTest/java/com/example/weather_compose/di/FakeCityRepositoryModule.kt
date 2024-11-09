package com.example.weather_compose.di

import com.example.weather_compose.data.CityRepository
import com.example.weather_compose.data.FakeCityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CityRepositoryModule::class]
)
object FakeCityRepositoryModule {

    @Singleton
    @Provides
    fun provideCityRepository(): CityRepository {
        return FakeCityRepository()
    }
}