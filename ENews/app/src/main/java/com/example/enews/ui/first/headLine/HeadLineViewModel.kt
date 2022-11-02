package com.example.enews.ui.first.headLine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.Retrofit.service
import com.example.enews.bean.headLine.headLineBean
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class HeadLineViewModel : ViewModel() {

    private val _data = MutableLiveData<headLineBean>()
    val data: LiveData<headLineBean> = _data

    init {}

    fun getContent() {
        service.getHeadLine()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(response: ResponseBody) {
                    val result: String = response.string()
                    val gson = Gson()
                    val bean = gson.fromJson(result, headLineBean::class.java)
                    _data.value = bean
                }

                override fun onError(e: Throwable) {
                    Log.d("123", "failure")
                }

                override fun onComplete() {
                }

            })
    }

}