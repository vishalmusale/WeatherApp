package com.vishalmusale.weather.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeather(
    var cityId : Int?,
    var name : String?,
    var country : String? = null,
    var lastDateTime : Int?,
    var curDateTime : Int?,
    var display_time : String? = "-",   // ToDO
    var lat : Double?,
    var lon : Double?,

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
    var icon : String?
) : Parcelable
