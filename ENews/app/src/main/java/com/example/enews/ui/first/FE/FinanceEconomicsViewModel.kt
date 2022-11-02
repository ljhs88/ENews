package com.example.enews.ui.first.FE

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.bean.FE.FEBean
import com.example.enews.bean.sport.sportBean
import com.google.gson.Gson
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class FinanceEconomicsViewModel : ViewModel() {
    init {}

    private val _data = MutableLiveData<FEBean>()
    val data : LiveData<FEBean> = _data

    fun getContent() {
        val url = "https://3g.163.com/touch/reconstruct/article/list/BA8EE5GMwangning/0-10.html"
        var content = Jsoup.connect(url).ignoreContentType(true).execute().body()
        content = content.substring(9, content.length-1)
        val bean = Gson().fromJson(content, FEBean::class.java)
        _data.postValue(bean)
    }
}