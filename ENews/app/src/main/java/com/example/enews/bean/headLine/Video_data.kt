package com.example.enews.bean.headLine

data class Video_data (
    var hevc_hdpreload_size : Int,
    var soloVideo: String,
    var hd_url: String,
    var sd_preload_size : Int,
    var sd_url: String,
    var hideAd : Boolean,
    var play_info: List<Play_info>,
    var play_info_hevc: String,
    var hevc_shdpreload_size : Int,
    var shd_url: String,
    var sd_size : Int,
    var shd_preload_size : Int,
    var duration : Int,
    var hevc_url: String,
    var knowledgeVideo : Int,
    var hevc_size : Int,
    var shd_size : Int,
    var hd_size : Int,
    var hd_preload_size : Int,
    var hevc_preload_size : Int,
    var ratio : Int
)