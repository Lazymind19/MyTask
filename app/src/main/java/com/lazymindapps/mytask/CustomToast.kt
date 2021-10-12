package com.lazymindapps.mytask

import android.content.Context
import android.widget.Toast

object CustomToast {

    fun toasts(context: Context,message:Any){
        Toast.makeText(context,message.toString(),Toast.LENGTH_LONG).show()

    }
}