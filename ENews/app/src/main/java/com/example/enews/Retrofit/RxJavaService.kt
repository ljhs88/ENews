package com.example.enews.Retrofit


import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface RxJavaService {

    @GET("nc/article/headline/T1348647853363/10-20.html")
    fun getEtm(): Observable<ResponseBody>
}