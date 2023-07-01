package com.vishalmusale.weather.ui.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.vishalmusale.weather.ui.weatherlist.WeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private var lat: Double = -1.0
    private var lon: Double = -1.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getDouble("lat") ?.let { lat -> this.lat = lat }
        arguments?.getDouble("lon") ?.let { lon -> this.lon = lon }
    }

    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result = viewModel.weather.value

        Log.d(Companion.TAG, "lat: $lat & lon:$lon")
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                Text(text = "Weather Fragment")
            }
        }
    }

    companion object {
        private const val TAG = "WeatherFragment"
    }
}