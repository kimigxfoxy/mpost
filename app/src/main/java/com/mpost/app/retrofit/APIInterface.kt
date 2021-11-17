package com.mpost.app.retrofit

import com.mpost.app.pojo.*
import retrofit2.Call
import retrofit2.http.*

internal interface APIInterface {

    @GET("Subscribers/GenerateOtp/{mobileNumber}")
    fun generateOTP(@Path ("mobileNumber") mobileNumber:String): Call<GenericResponse?>?

    @POST("Subscribers/ValidateOtp")
    fun validateOTP(@Body validateOtp: ValidateOtp): Call<GenericResponse?>?

    @POST("Subscribers/CreateSubscribers")
    fun createSubscriber(@Body subscriber: Subscriber): Call<GenericResponse?>?

    @GET("https://apistaging.mpost-app.com/staticdata/Cities/GetCitiesByName")
    fun getCitiesByName(@Query("countrycode") countryCode:String,
                        @Query("cityname") cityName: String):
            Call<List<City>?>?

    @GET("https://apistaging.mpost-app.com/staticdata/PostalOffices/GetPostalOfficesByArea")
    fun getPostalCodeByCityCodeAndSearchParam(@Query("citycode") cityCode:String,
                        @Query("areaname") areaName: String):
            Call<List<AreaCode>?>?

    @GET("https://apistaging.mpost-app.com/Subscribers/GetSubscriberDetails/{mobileNumber}")
    fun getSubscriberDetails(@Path ("mobileNumber") mobileNumber:String) : Call<SubscriberDetails?>?

}
