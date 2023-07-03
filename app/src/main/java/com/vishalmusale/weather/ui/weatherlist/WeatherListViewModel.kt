package com.vishalmusale.weather.ui.weatherlist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.repository.WeatherRepository
import com.vishalmusale.weather.ui.WeatherActivityViewModel
import com.vishalmusale.weather.util.Units
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val randomString : String,
    private val repository: WeatherRepository,
    @Named("API_Key") private val appId: String,
    @Named("SearchCity_Result_Limit") private val limit : Int,
    private  val unitPref : Units.UnitPreferences
) : ViewModel() {
    private var searchCityResult : MutableState<List<Weather>> = mutableStateOf(listOf())
    private val _currentUnit: MutableLiveData<Units.UnitSystem> = MutableLiveData(Units.UnitSystem.METRIC)
    val currentUnit: LiveData<Units.UnitSystem> = _currentUnit
    val searchQuery : MutableState<String> = mutableStateOf("")
    companion object {
        private const val TAG = "WeatherListViewModel"
    }
    init {
        viewModelScope.launch {
            unitPref.getCurrentUnit().collect {
                _currentUnit.value = it
            }
        }
    }

    fun searchCity(query : String) {
        viewModelScope.launch {
            val result = repository.searchCity(query, limit, appId)
            searchCityResult.value = result
            Log.d(TAG, "searchCity: $result")
            for(weather in searchCityResult.value) {
                Log.d(TAG, "city: ${weather.cityDisplayName}")
            }
        }
    }

    fun getSearchCityResult() : List<Weather>{
        return searchCityResult.value
    }

    fun clearSearchCityResult() {
        searchCityResult.value = listOf()
    }
}