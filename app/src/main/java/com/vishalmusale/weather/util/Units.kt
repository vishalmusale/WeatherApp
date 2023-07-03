package com.vishalmusale.weather.util

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vishalmusale.weather.ui.WeatherActivityViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "unit_preferences")

class Units {
    enum class UnitSystem {
        METRIC, IMPERIAL
    }

    class UnitPreferences(private val context: Context) {
        private val currentUnit: MutableState<UnitSystem> = mutableStateOf(UnitSystem.METRIC)
        private val unitKey = intPreferencesKey("unit_key")

        fun getCurrentUnit(): Flow<UnitSystem> {
            return context.dataStore.data.map { preferences ->
                val unitValue = preferences[unitKey] ?: UnitSystem.METRIC.ordinal
                UnitSystem.values()[unitValue]
            }
        }

        private suspend fun setUnitSystem(newUnit: UnitSystem) {
            currentUnit.value = newUnit
            context.dataStore.edit { preferences ->
                preferences[unitKey] = newUnit.ordinal
            }
        }

        suspend fun updateCurrentUnit(newUnit: Units.UnitSystem) {
            if(currentUnit.value != newUnit) {
                currentUnit.value = newUnit
                setUnitSystem(newUnit)
            }
        }
    }
}