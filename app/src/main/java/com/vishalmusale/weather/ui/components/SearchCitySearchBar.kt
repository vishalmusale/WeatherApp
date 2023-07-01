package com.vishalmusale.weather.ui.components

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vishalmusale.weather.R
import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.ui.weatherlist.WeatherListFragment
import com.vishalmusale.weather.ui.weatherlist.WeatherListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchCity(
    getSearchCityResult:() -> List<Weather>,
    searchCity: (String) -> Unit,
    clearSearchCityResult: () -> Unit,
    navController: NavController
) {
    val searchHint : String = "Search for a city"
    val searchQuery = remember { mutableStateOf("") } // Query for SearchBar
    val active = remember { mutableStateOf(false) } // Active state for SearchBar
    val searchResult = getSearchCityResult()

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = searchQuery.value,
        onQueryChange = {
            searchQuery.value = it
        },
        onSearch = {
            if(!searchQuery.value.isNullOrBlank()) {
                clearSearchCityResult()
                searchCity(searchQuery.value)
            }
//            searchQuery.value = ""
        },
        active = active.value,
        onActiveChange = {
            active.value = it
        },
        placeholder = {
            Text(text = searchHint)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search_Icon")
        },
        trailingIcon = {
            if(active.value) {
                Icon(
                    modifier = Modifier.clickable {
                        if(searchQuery.value.isNotEmpty()) {
                            searchQuery.value = ""
                            clearSearchCityResult()
                        } else {
                            active.value = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close_Icon"
                )
            }
        }
    ) {
        searchResult.forEach {
            //  Updating CityDisplay Name
            Row(
                modifier = Modifier
                    .padding(all = 14.dp)
                    .clickable {
                        if(it.lat != null && it.lon != null) {
                            var bundle = Bundle()
                            bundle.putDouble("lat", it.lat!!)
                            bundle.putDouble("lon", it.lon!!)

                            navController.navigate(R.id.action_weatherListFragment_to_weatherFragment, bundle)
                            clearSearchCityResult()
                        }
                    }
            ) {
                it.cityDisplayName?.let { city -> Text(text = city) }
            }
        }
    }
}
