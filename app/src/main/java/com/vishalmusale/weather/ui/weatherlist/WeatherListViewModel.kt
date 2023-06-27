package com.vishalmusale.weather.ui.weatherlist

import androidx.lifecycle.ViewModel
import com.vishalmusale.weather.di.RepositoryModule
import com.vishalmusale.weather.repository.CurrentWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val randomString : String,
    private val repository: CurrentWeatherRepository,
    @Named("API_Key") private val appId: String
) : ViewModel() {
    init {
        println("VIEWMODEL $randomString")
        println("App ID: $appId")
        println("Current Weather Repo: $repository")
    }
}