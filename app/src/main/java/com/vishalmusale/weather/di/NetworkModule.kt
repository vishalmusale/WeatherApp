package com.vishalmusale.weather.di

import com.google.gson.GsonBuilder
import com.vishalmusale.weather.network.OpenWeatherService
import com.vishalmusale.weather.network.model.CurrentWeatherDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideWeatherCurrentWeatherMapper() : CurrentWeatherDtoMapper {
        return CurrentWeatherDtoMapper()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherService() : OpenWeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(OpenWeatherService::class.java)
    }

    @Singleton
    @Provides
    @Named("API_Key")
    fun provideAPIKey() : String {
        return "5b315543d6148f7070c313eb2b9506de"
    }
}