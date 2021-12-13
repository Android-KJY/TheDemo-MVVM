package com.theone.common.ext

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.view.View
import android.view.Window
import java.util.*

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * @author The one
 * @date 2021-12-13 09:26
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

object ThemeUtil {

    var dates = arrayOf("05-12", "12-13")

    private fun isMourning(): Boolean {
        val today = Date().formatString(MM_DD)
        dates.forEach {
            if(today == it){
                return true
            }
        }
        return false
    }

    fun Window?.notifyMourning() {
        if (isMourning())
            this?.decorView?.setLayerType(View.LAYER_TYPE_HARDWARE, Paint().apply {
                colorFilter = ColorMatrixColorFilter(ColorMatrix().apply {
                    setSaturation(0.0f)
                })
            })
    }

}
