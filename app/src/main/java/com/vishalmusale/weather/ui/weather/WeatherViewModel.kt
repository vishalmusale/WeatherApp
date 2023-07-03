package com.vishalmusale.weather.ui.weather

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    @Named("SearchCity_Result_Limit") val searchCityResultLimit: Int,
    private  val unitPref : Units.UnitPreferences
) : ViewModel() {
    val weather : MutableState<Weather> = mutableStateOf(Weather())
    private val _currentUnit: MutableLiveData<Units.UnitSystem> = MutableLiveData(Units.UnitSystem.METRIC)
    val currentUnit: LiveData<Units.UnitSystem> = _currentUnit

    init {
        viewModelScope.launch {
            unitPref.getCurrentUnit().collect {
                _currentUnit.value = it
            }
        }
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}