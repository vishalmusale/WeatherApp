package com.vishalmusale.weather.network.model

import com.google.gson.annotations.SerializedName

data class SearchCityDto (

    @SerializedName("name"        ) var name       : String?     = null,
    @SerializedName("lat"         ) var lat        : Double?     = null,
    @SerializedName("lon"         ) var lon        : Double?     = null,
    @SerializedName("country"     ) var country    : String?     = null,
    @SerializedName("state"       ) var state      : String?     = null

)