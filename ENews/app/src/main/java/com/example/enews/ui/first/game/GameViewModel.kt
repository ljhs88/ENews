package com.example.enews.ui.first.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.bean.FE.FEBean
import com.example.enews.bean.game.gameBean
import com.google.gson.Gson
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class GameViewModel : ViewModel() {
    init {
        thread {
            getContent()
        }
    }

    private val _data = MutableLiveData<gameBean>()
    val data : LiveData<gameBean> = _data

    private fun getContent() {
        val url = "https://3g.163.com/touch/reconstruct/article/list/BAI6RHDKwangning/0-20.html"
        var content = Jsoup.connect(url).ignoreContentType(true).execute().body()
        content = content.substring(9, content.length-1)
        val bean = Gson().fromJson(content, gameBean::class.java)
        _data.postValue(bean)
    }
}