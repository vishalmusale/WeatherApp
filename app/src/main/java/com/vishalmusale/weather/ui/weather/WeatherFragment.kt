package com.vishalmusale.weather.ui.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vishalmusale.weather.ui.components.CurrentWeatherSection
import com.vishalmusale.weather.ui.components.DetailsSection
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()
    private var lat: Double? = null
    private var lon: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lat = arguments?.getDouble("lat")
        lon = arguments?.getDouble("lon")

        observeUnitChange()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val result = viewModel.weather.value
                val unit = viewModel.currentUnit.value
                CurrentWeatherSection(result)
                DetailsSection(currentWeather = result)

                if(lat != null && lon != null) {
                    viewModel.updateWeather(lat!!, lon!!, unit!!)
                }
            }
        }
    }

    private fun observeUnitChange() {
        viewModel.currentUnit.observe(this) { currentUnit ->
            Log.d(TAG, "observeUnitChange: weather changed to $currentUnit")
            if(lat != null && lon != null) {
                viewModel.updateWeather(lat!!, lon!!, currentUnit)
            }
        }
    }

    companion object {
        private const val TAG = "WeatherFragment"
    }
}