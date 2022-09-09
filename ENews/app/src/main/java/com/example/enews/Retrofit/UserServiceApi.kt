package com.example.enews.Retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface UserServiceApi {

    // headLine
    @GET("nc/article/headline/T1348647853363/10-20.html")
    fun getHeadLineUser(): Call<ResponseBody>

}