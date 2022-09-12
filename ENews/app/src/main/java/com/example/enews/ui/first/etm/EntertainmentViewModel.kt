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
    init {
        /*thread {
            getContent()
        }*/
        getContent()
    }

    private val _data = MutableLiveData<etmBean>()
    val data : LiveData<etmBean> = _data

    private fun getContent() {
        /*val url = "https://c.3g.163.com/nc/article/list/T1348648517839/0-20.html"
        val content = Jsoup.connect(url).ignoreContentType(true).execute().body()
        val bean = Gson().fromJson(content, etmBean::class.java)
        Log.d("123", content)
        Log.d("123", bean.T1348648517839.toString())
        _data.postValue(bean)*/
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