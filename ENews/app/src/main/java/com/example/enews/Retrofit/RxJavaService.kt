package com.example.enews.Retrofit


import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface RxJavaService {

    // headLine
    @GET("nc/article/headline/T1348647853363/0-20.html")
    fun getHeadLine(): Observable<ResponseBody>
    // etm
    @GET("nc/article/list/T1348648517839/0-20.html")
    fun getEtm(): Observable<ResponseBody>
    // sport
    @GET("nc/article/list/T1348649079062/0-20.html ")
    fun getSport() : Observable<ResponseBody>

}