package com.example.enews.Test

import android.util.Log
import com.example.enews.bean.headLine.headLineBean
import com.example.enews.Retrofit.UserServiceApi
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofitTest {

    /*fun myRetrofit() {
        var BASE_URL: String = "https://c.m.163.com/"

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val service: UserServiceApi = retrofit.create(UserServiceApi::class.java)

        service.apiDemo().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful()) {
                    var result: String = response.body()!!.string()
                    Log.d("123", "onResponse: " + result)
                    var gson = Gson()
                    val bean = gson.fromJson(result, headLineBean::class.java)
                    Log.d("123", bean.toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("123", "onFailure")
            }
        })
    }*/

    /*
    // 使用弱引用
    private val handler = UIHandler(WeakReference(this))
    private lateinit var bean: headLineBean

    private class UIHandler(val mWeakRef: WeakReference<headLine>) : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // 注意这里加个.run，代表是跑在this中的，方便直接访问外部类，当然也可以不用run，
            // 但是所有访问外部类的地方都要用mWeakRef.get()->的形式，写起来麻烦点
            mWeakRef.get()?.run {
                when (msg.what) {
                    1->{
                        val result = msg.data.get("news").toString()
                        val gson = Gson()
                        val bean = gson.fromJson(result, headLineBean::class.java)
                        Log.d("123", Thread.currentThread().name + " 数据变化了" + bean.toString())
                    } else -> {
                        context?.let {
                            ToastUtil.showMsg(it, "超时获取！")
                        }
                    }
                }
            }
        }
    }*/
}
