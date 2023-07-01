package com.vishalmusale.weather.network.model

import com.vishalmusale.weather.domain.model.Weather
import com.vishalmusale.weather.domain.util.DomainMapper
import com.vishalmusale.weather.util.Units

class SearchCityDtoMapper : DomainMapper<SearchCityDto, Weather> {
    override fun mapToDomainModel(model: SearchCityDto): Weather {
        return Weather(
            name = model.name,
            cityDisplayName = getCityDisplayName(model.name, model.state),
            state = model.state,
            country = model.country,
            lat = model.lat,
            lon = model.lon,
        )
    }

    private fun getCityDisplayName(name: String?, state: String?): String {
        var cityDisplayName = ""
        if(state == null || state == "-" || state == "") {
            name?.let { city -> cityDisplayName = city }
        } else {
            name?.let { city -> cityDisplayName = "$city, $state" }
        }
        return cityDisplayName
    }

    fun mapToDomainModelList(list: List<SearchCityDto>) : List<Weather> {
        return list.map { mapToDomainModel(it) }
    }

    override fun mapToDomainModel(model: SearchCityDto, unit: Units.UnitSystem): Weather {
        TODO("Not yet implemented")
    }

    override fun mapFromDomainModel(domainModel: Weather): SearchCityDto {
        TODO("Not yet implemented")
    }
}