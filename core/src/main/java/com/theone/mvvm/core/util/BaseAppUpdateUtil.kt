package com.theone.mvvm.core.util

import androidx.appcompat.app.AppCompatActivity
import com.theone.common.ext.isServiceExisted
import com.theone.mvvm.core.callback.IApkUpdate
import com.theone.mvvm.core.ui.activity.startAppUpdateActivity
import com.theone.mvvm.ext.qmui.hideLoadingDialog
import com.theone.mvvm.ext.qmui.showFailTipsDialog
import com.theone.mvvm.ext.qmui.showLoadingDialog
import com.theone.mvvm.ext.qmui.showSuccessTipsDialog

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
 * @date 2021-05-08 11:38
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseAppUpdateUtil<T:IApkUpdate>(val context:AppCompatActivity,val showCheck:Boolean) {

    protected open fun getDownloadServiceName():String = ""

    open fun checkUpdate(){
        if(isServiceExisted(context,getDownloadServiceName())){
            context.showFailTipsDialog("更新包正在下载中")
            return
        }else if (showCheck) {
            showCheckDialog()
        }
    }

    protected open fun T.onComplete(){
        hideCheckDialog()
        if(isNewVersion()){
            showNewVersionDialog(this)
        }else if(showCheck){
            showIsNewVersionDialog()
        }
    }

    protected open fun onError(errorMsg:String){
        hideCheckDialog()
        context.showFailTipsDialog(errorMsg)
    }

    protected open fun showCheckDialog(){
        context.showLoadingDialog("检查中")
    }

    protected open fun hideCheckDialog(){
        hideLoadingDialog()
    }

    protected open fun showIsNewVersionDialog(){
        context.showSuccessTipsDialog("已是最新版本")
    }

    protected open fun showNewVersionDialog(update:T){
        context.startAppUpdateActivity(update)
    }

}