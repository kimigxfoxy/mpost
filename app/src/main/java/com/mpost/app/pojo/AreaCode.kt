package com.mpost.app.pojo

import com.google.gson.annotations.SerializedName

class AreaCode {
    @SerializedName("id")
    var id:Int? = null
    @SerializedName("countryCode")
    var countryCode:String? = null
    @SerializedName("cityCode")
    var cityCode:String? = null
    @SerializedName("postalCode")
    var postalCode:String? = null
    @SerializedName("postalName")
    var postalName:String? = null
    @SerializedName("location")
    var location:String? = null
    @SerializedName("longitude")
    var longitude:String? = null
    @SerializedName("latitude")
    var latitude:String? = null
    @SerializedName("address")
    var address:String? = null
    @SerializedName("phoneNumber")
    var phoneNumber:String? = null
    @SerializedName("email")
    var email:String? = null

    constructor()
}