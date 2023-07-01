package com.vishalmusale.weather.ui.weather

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.network.model.WeatherDescription
import com.vishalmusale.weather.repository.WeatherRepository
import com.vishalmusale.weather.util.Units
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val randomString : String,
    private val repository: WeatherRepository,
    @Named("API_Key") private val appId: String,
    @Named("SearchCity_Result_Limit") val searchCityResultLimit: Int
) : ViewModel() {
    val weather : MutableState<Weather> = mutableStateOf(Weather())

    init {
        viewModelScope.launch {
            val result = repository.getCurrentWeatherCityId(
                5391959, Units.UnitSystem.METRIC, appId
            )
            Log.d(TAG, "ViewModel: $result")
            weather.value = result
        }
    }
    companion object {
        private const val TAG = "WeatherViewModel"
    }
}