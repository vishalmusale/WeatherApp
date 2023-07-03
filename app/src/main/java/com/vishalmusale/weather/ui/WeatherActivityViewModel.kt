package com.vishalmusale.weather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalmusale.weather.util.Units
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherActivityViewModel @Inject constructor(
    private  val unitPref : Units.UnitPreferences
)  : ViewModel() {
    private val _currentUnit: MutableLiveData<Units.UnitSystem> = MutableLiveData(Units.UnitSystem.METRIC)
    val currentUnit: LiveData<Units.UnitSystem> = _currentUnit

    init {
        viewModelScope.launch {
            unitPref.getCurrentUnit().collect {
                _currentUnit.value = it
            }
        }
    }

    fun updateCurrentUnit(newUnit: Units.UnitSystem) {
        viewModelScope.launch(Dispatchers.IO) {
            unitPref.updateCurrentUnit(newUnit)
        }
    }

    companion object {
        private const val TAG = "WeatherActivityViewMode"
    }
}