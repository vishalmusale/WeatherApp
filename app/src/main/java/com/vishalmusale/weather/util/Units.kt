package com.vishalmusale.weather.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "unit_preferences")

class Units {
    enum class UnitSystem {
        METRIC, IMPERIAL
    }

    class UnitPreferences(private val context: Context) {
        private val unitKey = intPreferencesKey("unit_key")

        fun getUnitSystem(): Flow<UnitSystem> {
            return context.dataStore.data.map { preferences ->
                val unitValue = preferences[unitKey] ?: UnitSystem.METRIC.ordinal
                UnitSystem.values()[unitValue]
            }
        }

        suspend fun setUnitSystem(unitSystem: UnitSystem) {
            context.dataStore.edit { preferences ->
                preferences[unitKey] = unitSystem.ordinal
            }
        }
    }
}