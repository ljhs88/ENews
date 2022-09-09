package com.example.enews.ui.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.bean.video.VideoBean
import com.google.gson.Gson
import org.jsoup.Jsoup
import kotlin.concurrent.thread


class VideoViewModel : ViewModel() {

    val data = MutableLiveData<VideoBean>()

    init {
        thread {
            videoRtf()
        }
    }

    private fun videoRtf() {
        val url = "https://c.3g.163.com/nc/video/home/0-10.html"
        val parse: String = Jsoup.connect(url).ignoreContentType(true).execute().body()
        data.postValue(Gson().fromJson(parse, VideoBean::class.java))
    }

}