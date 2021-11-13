package com.mpost.app.retrofit

import com.mpost.app.pojo.GenericResponse
import com.mpost.app.pojo.Subscriber
import com.mpost.app.pojo.ValidateOtp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface APIInterface {

    @GET("Subscribers/GenerateOtp/{mobileNumber}")
    fun generateOTP(@Path ("mobileNumber") mobileNumber:String): Call<GenericResponse?>?

    @POST("Subscribers/ValidateOtp")
    fun validateOTP(@Body validateOtp: ValidateOtp): Call<GenericResponse?>?

    @POST("Subscribers/CreateSubscribers")
    fun createSubscriber(@Body subscriber: Subscriber): Call<GenericResponse?>?

}
