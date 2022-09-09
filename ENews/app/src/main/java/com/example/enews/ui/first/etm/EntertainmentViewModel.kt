package com.example.enews.ui.first.etm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.Retrofit.service
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class EntertainmentViewModel : ViewModel() {
    init {
        getData()
    }

    val data = MutableLiveData<String>()

    private fun getData() {
        service.getEtm()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody>{
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onNext(t: ResponseBody) {
                    data.value = t.string()
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

            })


    }
}