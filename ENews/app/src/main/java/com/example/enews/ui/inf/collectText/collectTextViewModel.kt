package com.example.enews.ui.inf.collectText

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.bean.MS.MSBean
import com.example.enews.bean.inf.CollectTextBean
import com.google.gson.Gson
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class collectTextViewModel : ViewModel() {

    init {
        thread {
            getContent()
        }
    }

    private val _data = MutableLiveData<List<CollectTextBean>>()
    val data : MutableLiveData<List<CollectTextBean>> = _data

    private fun getContent() {
        val url = "https://3g.163.com/touch/reconstruct/article/list/BAI67OGGwangning/0-10.html"
        var content = Jsoup.connect(url).ignoreContentType(true).execute().body()
        content = content.substring(9, content.length-1)
        val bean = Gson().fromJson(content, MSBean::class.java)
        val collectTextList = mutableListOf<CollectTextBean>()
        for(item in bean.BAI67OGGwangning) {
            val title = item.title
            val image = item.imgsrc
            val author = item.source
            val url = item.url
            val collectTextBean = CollectTextBean(title, image, author, url)
            collectTextList.add(collectTextBean)
        }
        _data.postValue(collectTextList)
    }

}