package com.vishalmusale.weather.network.model

import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.domain.util.DomainMapper
import com.vishalmusale.weather.util.Units
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class WeatherDtoMapper() : DomainMapper<WeatherDto, Weather> {
    override fun mapToDomainModel(model: WeatherDto, unit: Units.UnitSystem): Weather {

        return Weather(
            cityId = model.id,
            name = model.name,
            cityDisplayName = model.name,
            country = model.sys?.country,
            lastDateTime = model.dt,
            curDateTime = model.dt,
            display_time = getDisplayTime(model.dt, model.timezone),
            lat = model.coord?.lat,
            lon = model.coord?.lon,
            temp = getTemp(model.main?.temp, unit),
            feels_like = getTemp(model.main?.feelsLike, unit),
            temp_max = getTemp(model.main?.tempMax, unit),
            temp_min = getTemp(model.main?.tempMin, unit),
            humidity = "${model.main?.humidity}%",
            pressure = "${model.main?.pressure} hPa",
            visibility = model.visibility?.let { convertDistance(it, unit) },
            wind = getSpeed(model.wind?.speed, unit),
            cloud = "${model.clouds?.all}%",
            main = model.weatherDescription[0].main,
            desciption = model.weatherDescription[0].description,
            icon = getIconUrl(model.weatherDescription[0].icon)
        )
    }

    private fun getIconUrl(icon: String?): String? {
        if(icon == null)
            return null;
        return "https://openweathermap.org/img/wn/$icon@2x.png"
    }

    private fun getDisplayTime(timestamp: Long?, timezone: Int?): String? {
        if(timestamp == null || timezone == null)
            return "-"
        val localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(timezone / 3600))
        return localDateTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
    }

    private fun getTemp(temp : Double?, unit: Units.UnitSystem) : String {
        if(temp == null)
            return "-"
        val degree = '\u00B0'
        return if(unit == Units.UnitSystem.IMPERIAL) {
            "$temp$degree F"
        } else {
            "$temp$degree C"
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

    private fun convertDistance(visibility: Int, unit: Units.UnitSystem) : String{
        return if(unit == Units.UnitSystem.METRIC) {
            if(visibility > 1000) "${getTwoDecimals((visibility / 1000.0))} km"
            else "$visibility meters"
        } else {
            if(visibility > 1609) "${getTwoDecimals(visibility * 0.000621371)} miles"
            else "${visibility * 3.281} ft"
        }
    }

    private fun getTwoDecimals(num: Double) : Double {
        return (num * 100.0).roundToInt() / 100.0
    }

    override fun mapFromDomainModel(domainModel: Weather): WeatherDto {
        TODO("Not yet implemented")
    }

    override fun mapToDomainModel(model: WeatherDto): Weather {
        TODO("Not yet implemented")
    }
}