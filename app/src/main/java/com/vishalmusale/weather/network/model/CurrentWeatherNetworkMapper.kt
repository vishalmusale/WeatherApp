package com.vishalmusale.weather.network.model

import android.content.Context
import androidx.compose.runtime.remember
import androidx.lifecycle.asLiveData
import com.vishalmusale.weather.domain.model.CurrentWeather
import com.vishalmusale.weather.domain.util.EntityMapper
import com.vishalmusale.weather.util.Units
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

//data class CurrentWeather(
//    var cityId : Int,
//    var name : String,
//    var country : String? = null,
//    var lastDateTime : Int,
//    var curDateTime : Int,
//    var display_time : String = "-",
//    var lat : Double,
//    var lon : Double,
//
//    var temp : String = "-",
//    var feels_like : String = "-",
//    var temp_min : String = "-",
//    var temp_max : String = "-",
//    var humidity : String = "-",
//    var pressure : String = "-",
//    var visibility : String = "-",
//    var wind : String = "-",
//    var cloud : String = "-",
//    var main : String = "-",
//    var desciption : String = "-",
//    var icon : String
//)
class CurrentWeatherNetworkMapper() : EntityMapper<CurrentWeatherEntity, CurrentWeather> {
    override fun mapFromEntity(entity: CurrentWeatherEntity, unit: Units.UnitSystem): CurrentWeather {

        return CurrentWeather(
            cityId = entity.id,
            name = entity.name,
            country = entity.sys?.country,
            lastDateTime = entity.dt,
            curDateTime = entity.dt,
            lat = entity.coord?.lat,
            lon = entity.coord?.lon,
            temp = getTemp(entity.main?.temp, unit),
            feels_like = getTemp(entity.main?.feelsLike, unit),
            temp_max = getTemp(entity.main?.tempMax, unit),
            temp_min = getTemp(entity.main?.tempMin, unit),
            humidity = "${entity.main?.humidity}%",
            pressure = "${entity.main?.pressure} hPa",
            wind = getSpeed(entity.wind?.speed, unit),
            cloud = "${entity.clouds?.all}%",
            main = entity.weather[0].main,
            desciption = entity.weather[0].description,
            icon = entity.weather[0].icon   // ToDo
        )
    }

    private fun getTemp(temp : Double?, unit: Units.UnitSystem) : String {
        if(temp == null)
            return "-"
        val degree = '\u00B0'
        return if(unit == Units.UnitSystem.IMPERIAL) {
            "$temp$degree C"
        } else {
            "$temp$degree F"
        }
    }

    private fun getSpeed(speed: Double?, unit: Units.UnitSystem) : String {
        if(speed == null)
            return "-"
        return if(unit == Units.UnitSystem.IMPERIAL) {
            "$speed miles/hour"
        } else {
            "$speed meter/sec"
        }
    }

    private fun getPressure(pressure: Double?, unit: Units.UnitSystem) : String {
        if(pressure == null)
            return "-"
        return "$pressure "
    }

    override fun mapToEntity(domainModel: CurrentWeather): CurrentWeatherEntity {
        TODO("Not yet implemented")
    }
}