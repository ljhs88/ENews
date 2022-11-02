package com.example.enews.ui.first.sport

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.Retrofit.service
import com.example.enews.bean.sport.sportBean
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class SportViewModel : ViewModel() {

    init {}

    private val _data = MutableLiveData<sportBean>()
    val data : LiveData<sportBean> = _data

    public fun getContent() {
        service.getSport()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<ResponseBody> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: ResponseBody) {
                    val content = t.string()
                    val bean = Gson().fromJson(content, sportBean::class.java)
                    _data.value = bean
                }

                override fun onError(e: Throwable) {
                    Log.d("123", "onFailure")
                }

                override fun onComplete() {

                }

            })
    }

}