package com.example.enews.util

import android.content.Context
import android.widget.Toast

class ToastUtil {
    companion object {
        private var toast: Toast? = null
        fun showMsg(context: Context, msg: String) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            } else {
                toast!!.setText(msg)
            }
            toast?.show()
        }
    }

}