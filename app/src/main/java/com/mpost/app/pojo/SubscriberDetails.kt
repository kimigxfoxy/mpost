package com.mpost.app.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class SubscriberDetails {
    @SerializedName("idNumber")
     var idNumber: String? = null

    @SerializedName("names")
     var names: String? = null

    @SerializedName("mobileNumber")
     var mobileNumber: String? = null

    @SerializedName("postalCode")
     var postalCode: String? = null

    @SerializedName("country")
     var country: String? = null

    @SerializedName("city")
     var city: String? = null

    @SerializedName("registeredOn")
     var registeredOn: String? = null

    @SerializedName("expiry")
     var expiry: String? = null

    @SerializedName("dob")
     var dob: String? = null

    @SerializedName("isPaid")
     var isPaid: String? = null

    @SerializedName("isExpired")
     var isExpired: String? = null

    @SerializedName("isDisabled")
     var isDisabled: String? = null

    @SerializedName("dateDisabled")
     var dateDisabled: String? = null

    @SerializedName("googleToken")
     var googleToken: String? = null

    @SerializedName("emailaddress")
     var emailaddress: String? = null

    @SerializedName("address")
     var address: String? = null

    @SerializedName("isAgent")
     var isAgent: String? = null

    constructor(
        idNumber: String?,
        names: String?,
        mobileNumber: String?,
        postalCode: String?,
        country: String?,
        city: String?,
        registeredOn: String?,
        expiry: String?,
        dob: String?,
        isPaid: String?,
        isExpired: String?,
        isDisabled: String?,
        dateDisabled: String?,
        googleToken: String?,
        emailaddress: String?,
        address: String?,
        isAgent: String?
    ) {
        this.idNumber = idNumber
        this.names = names
        this.mobileNumber = mobileNumber
        this.postalCode = postalCode
        this.country = country
        this.city = city
        this.registeredOn = registeredOn
        this.expiry = expiry
        this.dob = dob
        this.isPaid = isPaid
        this.isExpired = isExpired
        this.isDisabled = isDisabled
        this.dateDisabled = dateDisabled
        this.googleToken = googleToken
        this.emailaddress = emailaddress
        this.address = address
        this.isAgent = isAgent
    }
}