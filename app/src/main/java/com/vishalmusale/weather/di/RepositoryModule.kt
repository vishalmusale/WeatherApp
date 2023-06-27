package com.vishalmusale.weather.di

import com.vishalmusale.weather.network.OpenWeatherService
import com.vishalmusale.weather.network.model.WeatherDtoMapper
import com.vishalmusale.weather.repository.WeatherRepository
import com.vishalmusale.weather.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(
        openWeatherService: OpenWeatherService,
        weatherDtoMapper: WeatherDtoMapper
    ) : WeatherRepository {
        return WeatherRepositoryImpl(openWeatherService, weatherDtoMapper)
    }
}