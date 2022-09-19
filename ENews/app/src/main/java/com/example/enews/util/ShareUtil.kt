package com.example.enews.util

import android.content.Context
import android.content.SharedPreferences
import com.example.enews.bean.inf.CollectTextBean2
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

    fun setDataList(context: Context, key: String?, datalist: List<CollectTextBean2>?) {
        if (null == datalist || datalist.isEmpty()) return
        val gson = Gson()
        //转换成json数据，再保存
        val strJson = gson.toJson(datalist)
        val editor = getPref(context).edit()
        editor.clear()
        editor.putString(key, strJson)
        editor.apply()
    }

    fun getDataList(context: Context, key: String?): List<CollectTextBean2>? {
        var datalist: List<CollectTextBean2> = ArrayList()
        val pref = getPref(context)
        val strJson: String = pref.getString(key, null) ?: return datalist
        val gson = Gson()
        datalist = gson.fromJson(strJson, object : TypeToken<List<CollectTextBean2>?>() {}.type)
        return datalist
    }
}