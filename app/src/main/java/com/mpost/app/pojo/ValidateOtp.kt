package com.mpost.app.pojo

import com.google.gson.annotations.SerializedName

class ValidateOtp {
    @SerializedName("mobileNumber")
    var mobileNumber:String? = null
    @SerializedName("userOTP")
    var userOTP:String? = null

    constructor(mobileNumber: String?, userOTP: String?) {
        this.mobileNumber = mobileNumber
        this.userOTP = userOTP
    }
}