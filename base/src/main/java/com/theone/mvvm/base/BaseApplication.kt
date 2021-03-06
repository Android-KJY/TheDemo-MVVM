package com.theone.mvvm.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import kotlin.properties.Delegates


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
 * @date 2021/2/22 0022
 * @describe 提供一个功能--在Activity/fragment中获取Application级别的ViewModel
 * @email 625805189@qq.com
 * @remark
 */

val appContext: Application by lazy { BaseApplication.app }

abstract class BaseApplication : Application(), ViewModelStoreOwner {

    companion object{
        lateinit var app: Application
        var DEBUG by Delegates.notNull<Boolean>()
    }

    abstract fun isDebug():Boolean

    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun onCreate() {
        super.onCreate()
        app = this
        DEBUG = isDebug()
        mAppViewModelStore = ViewModelStore()
        init(this)
    }

    protected open fun init(application: Application){
        QMUISwipeBackActivityManager.init(application)
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

}