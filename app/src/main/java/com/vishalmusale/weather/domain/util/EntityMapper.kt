package com.vishalmusale.weather.domain.util

import android.content.Context
import com.vishalmusale.weather.util.Units

interface EntityMapper <Entity, DomainModel>{
    fun mapFromEntity(entity: Entity, unit: Units.UnitSystem) : DomainModel
    fun mapToEntity(domainModel: DomainModel) : Entity
}