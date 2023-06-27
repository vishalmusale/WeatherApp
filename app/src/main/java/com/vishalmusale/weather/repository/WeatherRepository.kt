package com.vishalmusale.weather.repository

import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.util.Units

interface WeatherRepository {
    suspend fun getCurrentWeatherLatLon (
        lat: Double,
        lon: Double,
        units: Units.UnitSystem,
        addId: String
    ) : Weather

    suspend fun getCurrentWeatherCityName (
        city: String,
        units: Units.UnitSystem,
        addId: String
    ) : Weather

    suspend fun getCurrentWeatherCityId (
        cityId: Int,
        units: Units.UnitSystem,
        addId: String
    ) : Weather
}