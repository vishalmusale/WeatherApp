package com.vishalmusale.weather.repository

import android.util.Log
import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.network.OpenWeatherService
import com.vishalmusale.weather.network.model.SearchCityDtoMapper
import com.vishalmusale.weather.network.model.WeatherDtoMapper
import com.vishalmusale.weather.util.Units

class WeatherRepositoryImpl(
    private val openWeatherService: OpenWeatherService,
    private val mapper: WeatherDtoMapper,
    private val searchCityMapper: SearchCityDtoMapper
) : WeatherRepository{
    override suspend fun getCurrentWeatherLatLon(
        lat: Double,
        lon: Double,
        units: Units.UnitSystem,
        addId: String
    ): Weather {
        val result = openWeatherService.getCurrentWeatherLatLon(lat, lon, units.name, addId)
        return mapper.mapToDomainModel(result,units)
    }

    override suspend fun getCurrentWeatherCityName(
        city: String,
        units: Units.UnitSystem,
        addId: String
    ): Weather {
        val result = openWeatherService.getCurrentWeatherCityName(city, units.name, addId)
        return mapper.mapToDomainModel(result, units)
    }

    override suspend fun getCurrentWeatherCityId(
        cityId: Int,
        units: Units.UnitSystem,
        addId: String
    ): Weather {
        val result = openWeatherService.getCurrentWeatherCityId(cityId, units.name, addId)
        return mapper.mapToDomainModel(result, units)
    }

    override suspend fun searchCity(
        query: String,
        limit: Int,
        appId: String
    ): List<Weather> {
        Log.d(TAG, "searchCity: limit=$limit, appId:$appId")
        val result = openWeatherService.searchCity(query, limit, appId)
        Log.d(Companion.TAG, "searchCity: $result")
        return searchCityMapper.mapToDomainModelList(result)
    }

    companion object {
        private const val TAG = "WeatherRepositoryImpl"
    }
}