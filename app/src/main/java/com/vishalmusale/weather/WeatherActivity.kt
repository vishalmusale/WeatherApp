package com.vishalmusale.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.vishalmusale.weather.network.OpenWeatherService
import com.vishalmusale.weather.util.Units
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val service = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(OpenWeatherService::class.java)

        CoroutineScope(IO).launch {
            val response = service.getCurrentWeatherCityId(1266285,
                Units.UnitSystem.METRIC.name, "5b315543d6148f7070c313eb2b9506de")
            Log.d(Companion.TAG, "onCreate: $response")
        }
    }

    companion object {
        private const val TAG = "WeatherActivity"
    }
}