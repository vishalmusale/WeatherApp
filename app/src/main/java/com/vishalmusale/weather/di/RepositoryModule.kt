package com.vishalmusale.weather.di

import com.vishalmusale.weather.network.OpenWeatherService
import com.vishalmusale.weather.network.model.CurrentWeatherDtoMapper
import com.vishalmusale.weather.repository.CurrentWeatherRepository
import com.vishalmusale.weather.repository.CurrentWeatherRepositoryImpl
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
    fun provideCurrentWeatherRepository(
        openWeatherService: OpenWeatherService,
        currentWeatherDtoMapper: CurrentWeatherDtoMapper
    ) : CurrentWeatherRepository {
        return CurrentWeatherRepositoryImpl(openWeatherService, currentWeatherDtoMapper)
    }
}