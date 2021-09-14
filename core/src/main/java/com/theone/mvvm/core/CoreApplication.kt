package com.theone.mvvm.core

import android.app.Application
import com.hjq.toast.ToastUtils
import com.theone.common.ext.LogInit
import com.theone.mvvm.base.BaseApplication
import com.theone.mvvm.core.BuildConfig.DEBUG
import com.theone.mvvm.core.ext.initLoadSir
import com.theone.mvvm.core.util.NotificationManager

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
 * @date 2021/3/23 0022
 * @describe 提供默认
 * @email 625805189@qq.com
 * @remark
 */
abstract class CoreApplication : BaseApplication() {

    override fun init(application: Application) {
        super.init(application)
        LogInit(DEBUG)
        NotificationManager.getInstance().register()
        initLoadSir()
        ToastUtils.init(this)
    }

}