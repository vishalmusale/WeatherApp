package com.vishalmusale.weather.ui.weather

import androidx.lifecycle.ViewModel
import com.vishalmusale.weather.repository.CurrentWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val randomString : String,
    private val repository: CurrentWeatherRepository,
    @Named("API_Key") private val appId: String
) : ViewModel() {

}