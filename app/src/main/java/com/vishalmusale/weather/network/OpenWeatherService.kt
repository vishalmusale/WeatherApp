package com.vishalmusale.weather.network

import com.vishalmusale.weather.network.model.SearchCityDto
import com.vishalmusale.weather.network.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherLatLon(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("units") units: String,
        @Query("appid") appId : String
    ) : WeatherDto

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherCityName(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appId : String
    ) : WeatherDto

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherCityId(
        @Query("id") cityId: Int,
        @Query("units") units: String,
        @Query("appid") appId : String
    ) : WeatherDto

    @GET("geo/1.0/direct")
    suspend fun searchCity(
        @Query("q") query: String,
        @Query("limit") limit : Int,
        @Query("appid") appId : String
    ) : List<SearchCityDto>
}