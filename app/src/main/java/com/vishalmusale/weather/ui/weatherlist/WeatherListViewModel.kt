package com.vishalmusale.weather.ui.weatherlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.network.model.WeatherDescription
import com.vishalmusale.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val randomString : String,
    private val repository: WeatherRepository,
    @Named("API_Key") private val appId: String
) : ViewModel() {
    private val weatherDescriptionList : MutableState<List<Weather>> = mutableStateOf(listOf(Weather()))
    companion object {
        private const val TAG = "WeatherListViewModel"
    }
}