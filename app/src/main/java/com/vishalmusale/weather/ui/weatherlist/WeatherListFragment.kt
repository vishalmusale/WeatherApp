package com.vishalmusale.weather.ui.weatherlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.vishalmusale.weather.R
import com.vishalmusale.weather.ui.components.searchCity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherListFragment : Fragment() {
    private val viewModel: WeatherListViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column (modifier = Modifier.padding(14.dp)) {
                    //  SearchBar
                    searchCity(
                        viewModel::getSearchCityResult,
                        viewModel::searchCity,
                        viewModel::clearSearchCityResult,
                        findNavController()
                    )


                    Spacer(Modifier.padding(10.dp))
                    Text(text = "Weather List Fragment")
                    Spacer(Modifier.padding(10.dp))
                    Button(onClick = {
                        findNavController().navigate(R.id.action_weatherListFragment_to_weatherFragment)
                    }) {
                        Text(text = "Go to Weather")
                    }
                }

            }
        }
    }

    companion object {
        private const val TAG = "WeatherListFragment"
    }
}