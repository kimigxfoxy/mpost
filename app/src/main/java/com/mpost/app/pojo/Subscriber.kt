package com.mpost.app.pojo
import com.google.gson.annotations.SerializedName

class Subscriber {
    @SerializedName("idNumber")
    var idNumber:String? = null
    @SerializedName("names")
    var names:String? = null
    @SerializedName("mobileNumber")
    var mobileNumber:String? = null
    @SerializedName("postalCode")
    var postalCode:String? = null
    @SerializedName("countryCode")
    var countryCode:String? = null
    @SerializedName("emailAddress")
    var emailAddress:String? = null
    @SerializedName("gender")
    var gender:String? = null
    @SerializedName("dob")
    var dob:String? = null
    @SerializedName("refereeString")
    var refereeString:String? = null

    constructor(
        idNumber: String?,
        names: String?,
        mobileNumber: String?,
        postalCode: String?,
        countryCode: String?,
        emailAddress: String?,
        gender: String?,
        dob: String?,
        refereeString: String?
    ) {
        this.idNumber = idNumber
        this.names = names
        this.mobileNumber = mobileNumber
        this.postalCode = postalCode
        this.countryCode = countryCode
        this.emailAddress = emailAddress
        this.gender = gender
        this.dob = dob
        this.refereeString = refereeString
    }
}