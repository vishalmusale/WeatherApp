package com.vishalmusale.weather.repository

import com.vishalmusale.weather.domain.model.CurrentWeather
import com.vishalmusale.weather.network.OpenWeatherService
import com.vishalmusale.weather.network.model.CurrentWeatherDtoMapper
import com.vishalmusale.weather.util.Units

class CurrentWeatherRepositoryImpl(
    private val openWeatherService: OpenWeatherService,
    private val mapper: CurrentWeatherDtoMapper
) : CurrentWeatherRepository{
    override suspend fun getCurrentWeatherLatLon(
        lat: Double,
        lon: Double,
        units: Units.UnitSystem,
        addId: String
    ): CurrentWeather {
        val result = openWeatherService.getCurrentWeatherLatLon(lat, lon, units.name, addId)
        return mapper.mapToDomainModel(result,units)
    }

    override suspend fun getCurrentWeatherCityName(
        city: String,
        units: Units.UnitSystem,
        addId: String
    ): CurrentWeather {
        val result = openWeatherService.getCurrentWeatherCityName(city, units.name, addId)
        return mapper.mapToDomainModel(result, units)
    }

    override suspend fun getCurrentWeatherCityId(
        cityId: Int,
        units: Units.UnitSystem,
        addId: String
    ): CurrentWeather {
        val result = openWeatherService.getCurrentWeatherCityId(cityId, units.name, addId)
        return mapper.mapToDomainModel(result, units)
    }
}