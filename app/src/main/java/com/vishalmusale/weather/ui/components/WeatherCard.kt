package com.vishalmusale.weather.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishalmusale.weather.domain.model.Weather

@Composable
fun WeatherCard (
    weather: Weather,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation()
    ) {
        Row ( horizontalArrangement = Arrangement.SpaceBetween ) {
                //  Left side
                Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                    //  City, Time
                    Column(modifier = Modifier.fillMaxWidth()) {
                        weather.name?.let {
                            Text(
                                text = it,
                                fontSize = 28.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(6.dp))
                        weather.display_time?.let {
                            Text(text = it,
                            fontSize = 14.sp)
                        }
                    }

                    // Weather main
                    Column(modifier = Modifier.fillMaxWidth()) {
                        weather.main?.let {
                            Text(text = it, fontSize = 18.sp)
                        }
                    }
                }

                //  Right side
                Column(modifier = Modifier.fillMaxWidth(0.35f),
                    horizontalAlignment = Alignment.End) {
                    //  Current Temp
                    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                        weather.temp?.let {
                            Text(text = it,
                            fontSize = 48.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                    }
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        weather.temp_max?.let {
                            Text(text = "H: $it",
                                fontSize = 14.sp,
                                modifier = Modifier.wrapContentWidth(Alignment.Start)
                            )
                        }
                        weather.temp_min?.let {
                            Text(text = "L: $it",
                                fontSize = 14.sp,
                                modifier = Modifier.wrapContentWidth(
                                Alignment.End)
                            )
                        }
                    }
                }
        }
    }
}