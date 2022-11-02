package com.example.enews.ui.first.etm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.Retrofit.service
import com.example.enews.bean.entertainment.etmBean
import com.example.enews.bean.headLine.headLineBean
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class EntertainmentViewModel() : ViewModel() {
    init {}

    private val _data = MutableLiveData<etmBean>()
    val data : LiveData<etmBean> = _data

    fun getContent() {
        service.getEtm()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody>{
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: ResponseBody) {
                    val bean = Gson().fromJson(t.string(), etmBean::class.java)
                    _data.value = bean
                }

                override fun onError(e: Throwable) {
                    Log.d("123", "获取失败")
                }

                override fun onComplete() {}

            })
    }
}