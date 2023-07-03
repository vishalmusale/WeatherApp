package com.vishalmusale.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.gson.GsonBuilder
import com.vishalmusale.weather.R
import com.vishalmusale.weather.network.OpenWeatherService
import com.vishalmusale.weather.ui.weatherlist.WeatherListViewModel
import com.vishalmusale.weather.util.Units
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    @Inject
    lateinit var unitPref : Units.UnitPreferences
    private val viewModel: WeatherActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        observeUnitChange()
    }

    private fun observeUnitChange() {
       viewModel.currentUnit.observe(this) { currentUnit ->
            Log.d(TAG, "observeUnitChange: ${currentUnit.name}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_celsius -> {
                viewModel.updateCurrentUnit(Units.UnitSystem.METRIC)
                true
            }
            R.id.menu_fahrenheit -> {
                viewModel.updateCurrentUnit(Units.UnitSystem.IMPERIAL)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "WeatherActivity"
    }
}