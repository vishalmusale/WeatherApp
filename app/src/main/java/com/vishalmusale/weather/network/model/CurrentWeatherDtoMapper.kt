package com.vishalmusale.weather.network.model

import com.vishalmusale.weather.domain.model.CurrentWeather
import com.vishalmusale.weather.domain.util.DomainMapper
import com.vishalmusale.weather.util.Units

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
class CurrentWeatherDtoMapper() : DomainMapper<CurrentWeatherDto, CurrentWeather> {
    override fun mapToDomainModel(model: CurrentWeatherDto, unit: Units.UnitSystem): CurrentWeather {

        return CurrentWeather(
            cityId = model.id,
            name = model.name,
            country = model.sys?.country,
            lastDateTime = model.dt,
            curDateTime = model.dt,
            lat = model.coord?.lat,
            lon = model.coord?.lon,
            temp = getTemp(model.main?.temp, unit),
            feels_like = getTemp(model.main?.feelsLike, unit),
            temp_max = getTemp(model.main?.tempMax, unit),
            temp_min = getTemp(model.main?.tempMin, unit),
            humidity = "${model.main?.humidity}%",
            pressure = "${model.main?.pressure} hPa",
            wind = getSpeed(model.wind?.speed, unit),
            cloud = "${model.clouds?.all}%",
            main = model.weather[0].main,
            desciption = model.weather[0].description,
            icon = model.weather[0].icon   // ToDo
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

    override fun mapFromDomainModel(domainModel: CurrentWeather): CurrentWeatherDto {
        TODO("Not yet implemented")
    }
}