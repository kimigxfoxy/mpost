package com.mpost.app.pojo

import com.google.gson.annotations.SerializedName

class City {
    @SerializedName("id")
    var id:Int? = null
    @SerializedName("countryCode")
    var countryCode:Int? = null
    @SerializedName("cityName")
    var cityName:String? = null
    @SerializedName("cityShortName")
    var cityShortName:String? = null

    constructor(id: Int?, countryCode: Int?, cityName: String?, shortName: String?) {
        this.id = id
        this.countryCode = countryCode
        this.cityName = cityName
        this.cityShortName = shortName
    }
}