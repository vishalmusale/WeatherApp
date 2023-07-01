package com.vishalmusale.weather.ui.weatherlist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.network.model.WeatherDescription
import com.vishalmusale.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val randomString : String,
    private val repository: WeatherRepository,
    @Named("API_Key") private val appId: String,
    @Named("SearchCity_Result_Limit") private val limit : Int
) : ViewModel() {
    private var searchCityResult : MutableState<List<Weather>> = mutableStateOf(listOf())

    val searchQuery : MutableState<String> = mutableStateOf("")
    companion object {
        private const val TAG = "WeatherListViewModel"
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