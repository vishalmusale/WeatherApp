package com.vishalmusale.weather.domain.util

import com.vishalmusale.weather.util.Units

interface DomainMapper <T, DomainModel>{
    fun mapToDomainModel(model: T, unit: Units.UnitSystem) : DomainModel
    fun mapFromDomainModel(domainModel: DomainModel) : T
}