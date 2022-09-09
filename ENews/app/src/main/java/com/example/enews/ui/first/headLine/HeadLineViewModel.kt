package com.example.enews.ui.first.headLine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.Retrofit.userServiceApi
import com.example.enews.bean.headLine.headLineBean
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadLineViewModel : ViewModel() {

    val data = MutableLiveData<headLineBean>()

    init {
        myRetrofit()
    }

    @JvmName("getData1")
    fun setData() {
        myRetrofit()
    }

    private fun myRetrofit() {
        userServiceApi.getHeadLineUser().enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val result: String = response.body()!!.string()
                    val gson = Gson()
                    val bean = gson.fromJson(result, headLineBean::class.java)
                    data.value = bean
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("123", "onFailure")
            }
        })
    }

}