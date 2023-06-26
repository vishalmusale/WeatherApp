package com.vishalmusale.weather.repository

import com.vishalmusale.weather.domain.model.CurrentWeather
import com.vishalmusale.weather.util.Units

interface CurrentWeatherRepository {
    suspend fun getCurrentWeatherLatLon (
        lat: Double,
        lon: Double,
        units: Units.UnitSystem,
        addId: String
    ) : CurrentWeather

    suspend fun getCurrentWeatherCityName (
        city: String,
        units: Units.UnitSystem,
        addId: String
    ) : CurrentWeather

    suspend fun getCurrentWeatherCityId (
        cityId: Int,
        units: Units.UnitSystem,
        addId: String
    ) : CurrentWeather
}