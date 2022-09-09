package com.example.enews.Retrofit


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// retrofit网络请求
val userServiceApi: UserServiceApi by lazy {
    val retrofit = Retrofit.Builder()//https://3g.163.com/touch/nc/api/video/recommend/Video_Recom/10-20.do?callback=index2018_header_main
        .baseUrl("https://c.3g.163.com/")//https://c.3g.163.com/nc/video/home/0-10.html
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    retrofit.create(UserServiceApi::class.java)
}

val service: RxJavaService by lazy {
    val retrofit = Retrofit.Builder()//https://3g.163.com/touch/nc/api/video/recommend/Video_Recom/10-20.do?callback=index2018_header_main
        .baseUrl("https://c.3g.163.com/")//https://c.3g.163.com/nc/video/home/0-10.html
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    retrofit.create(RxJavaService::class.java)
}