package com.vishalmusale.weather.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishalmusale.weather.domain.model.Weather

@Composable
fun CurrentWeatherSection(currentWeather: Weather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(Color.White)
    ) {
        Row (
            Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier.fillMaxWidth(0.75f)
            ) {
                Text(
                    text = currentWeather.cityDisplayName!!,
                    fontSize = 36.sp
                )
                Text(
                    text = currentWeather.display_time!!,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = { /*TODO Add to the weather list*/ }) {
                Text(text = "Add")
            }
        }
        Row (
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
                .height(150.dp)
                ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(top = 25.dp),
                verticalArrangement = Arrangement.Center
                    ) {
                Text(
                    text = currentWeather.temp!!,
                    fontSize = 48.sp
                )
                Text(
                    text = currentWeather.desciption!!,
                    fontSize = 18.sp
                )
            }

            //  Image
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
                    ) {
                currentWeather.icon?. let {
                    val image = loadPicture(url = currentWeather.icon!!, defaultImage = DEFAULT_WEATHER_IMAGE).value
                    image?.let { img ->
                        Image(bitmap = img.asImageBitmap(), contentDescription = "Weather Icon", modifier = Modifier.height(150.dp), contentScale = ContentScale.FillHeight)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsSection(currentWeather: Weather) {
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 2)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                WeatherDetailSection(currentWeather)
            }
        }
    }
}

@Composable
private fun WeatherDetailSection(currentWeather: Weather) {
    CurrentWeatherDetailRow(
        title1 = "Feels like",
        value1 = currentWeather.feels_like!!,
        title2 = "Humidity",
        value2 = currentWeather.humidity!!
    )
    CurrentWeatherDetailRow(
        title1 = "Wind",
        value1 = currentWeather.wind!!,
        title2 = "Visibility",
        value2 = currentWeather.visibility!!
    )
}

@Composable
fun CurrentWeatherDetailRow(title1: String, value1: String, title2: String, value2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CurrentWeatherDetailCard(title = title1, value = value1)
        CurrentWeatherDetailCard(title = title2, value = value2)
    }
}

@Composable
private fun CurrentWeatherDetailCard(title: String, value: String) {
    Card(
        modifier = Modifier.size(180.dp),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp), Alignment.TopStart) {
            Text(text = title, fontSize = 22.sp)
        }
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Text(
                text = value,
                fontSize = 24.sp
            )
        }
    }
}