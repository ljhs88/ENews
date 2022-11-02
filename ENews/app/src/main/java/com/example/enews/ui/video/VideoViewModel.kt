package com.example.enews.ui.video

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enews.bean.video.VideoBean
import com.google.gson.Gson
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.random.nextUInt


class VideoViewModel : ViewModel() {

    private val _data = MutableLiveData<VideoBean>()
    val data: LiveData<VideoBean> = _data

    init {}

    @SuppressLint("SimpleDateFormat")
    fun videoRtf() {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        var s = simpleDateFormat.format(Date())
        s = s.substring(6,s.length)
        var start = s.toInt()
        if (start > 30) start = 60-start
        val url = "https://c.3g.163.com/nc/video/home/$start-20.html"
        val parse: String = Jsoup.connect(url).ignoreContentType(true).execute().body()
        _data.postValue(Gson().fromJson(parse, VideoBean::class.java))
    }

}