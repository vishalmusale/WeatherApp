package com.vishalmusale.weather.ui.weatherlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val randomString : String
) : ViewModel() {
    init {
        println("VIEWMODEL $randomString")
    }
}