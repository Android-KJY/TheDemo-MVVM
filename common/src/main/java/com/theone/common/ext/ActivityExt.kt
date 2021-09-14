package com.theone.common.ext

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

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
 * @date 2021-04-27 13:15
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

fun Activity.startActivity(intent: Intent, finish: Boolean = false) {
    startActivity(intent)
    if (finish) {
        finish()
    }
}

fun Activity.startActivity(target: Class<*>, finish: Boolean = false) {
    startActivity(Intent(this, target),finish)
}