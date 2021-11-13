package com.mpost.app.pojo

import com.google.gson.annotations.SerializedName

class GenericResponse {
    @SerializedName("code")
    var code:String? = null
    @SerializedName("message")
    var message:String? = null

}