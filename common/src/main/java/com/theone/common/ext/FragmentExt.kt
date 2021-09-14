package com.theone.common.ext

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment


fun Fragment.getView(layoutId: Int): View {
   return layoutInflater.inflate(layoutId, null)
}

/**
 * dp值转换为px
 */
fun Fragment.dp2px(dp: Int): Int {
    return requireContext().dp2px(dp)
}

/**
 * px值转换成dp
 */
fun Fragment.px2dp(px: Int): Int {
   return requireContext().px2dp(px)
}

fun Fragment.startActivity(target:Class<*>, finish:Boolean = false){
    requireActivity().startActivity(target,finish)
}

fun Fragment.startActivity(intent: Intent, finish: Boolean = false){
    requireActivity().startActivity(intent,finish)
}

fun Fragment.startWebView(url:String){
    startActivity(Intent().apply {
        action = "android.intent.action.VIEW"
        data = Uri.parse(url)
    })
}