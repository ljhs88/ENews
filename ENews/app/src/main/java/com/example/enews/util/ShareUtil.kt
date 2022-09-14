package com.example.enews.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ShareUtil {

    private var pref: SharedPreferences?=null

    private fun getPref(context: Context):SharedPreferences{
        if(pref==null){
            pref=context.getSharedPreferences("default",Context.MODE_PRIVATE)
        }
        return pref!!
    }

    fun <T> setDataList(context: Context, key: String?, datalist: List<T>?) {
        if (null == datalist || datalist.isEmpty()) return
        val gson = Gson()
        //转换成json数据，再保存
        val strJson = gson.toJson(datalist)
        val editor = getPref(context).edit()
        editor.clear()
        editor.putString(key, strJson)
        editor.apply()
    }

    fun <T> getDataList(context: Context, key: String?): List<T>? {
        var datalist: List<T> = ArrayList()
        val pref = getPref(context)
        val strJson: String = pref.getString(key, null) ?: return datalist
        val gson = Gson()
        datalist = gson.fromJson(strJson, object : TypeToken<List<T>?>() {}.type)
        return datalist
    }
}