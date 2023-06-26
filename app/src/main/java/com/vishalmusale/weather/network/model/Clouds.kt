package com.vishalmusale.weather.network.model

import com.google.gson.annotations.SerializedName


data class Clouds (

    @SerializedName("all" ) var all : Int? = null

)