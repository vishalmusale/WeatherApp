package com.vishalmusale.weather.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    var cityId : Int? = null,
    var name : String? = null,
    var country : String? = null,
    var lastDateTime : Int? = null,
    var curDateTime : Int? = null,
    var display_time : String? = "-",   // ToDO
    var lat : Double? = null,
    var lon : Double? = null,

    var temp : String? = "-",
    var feels_like : String? = "-",
    var temp_min : String? = "-",
    var temp_max : String? = "-",
    var humidity : String? = "-",
    var pressure : String? = "-",
    var visibility : String? = "-",
    var wind : String? = "-",
    var cloud : String? = "-",
    var main : String? = "-",
    var desciption : String? = "-",
    var icon : String? = null
) : Parcelable
