package com.vishalmusale.weather.network

import com.vishalmusale.weather.network.model.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherLatLon(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("units") units: String,
        @Query("appid") appId : String
    ) : CurrentWeatherDto

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherCityName(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appId : String
    ) : CurrentWeatherDto

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherCityId(
        @Query("id") cityId: Int,
        @Query("units") units: String,
        @Query("appid") appId : String
    ) : CurrentWeatherDto
}