package com.theone.mvvm.core.ext

import android.annotation.SuppressLint
import android.app.Activity
import androidx.fragment.app.Fragment
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.core.widge.dialog.ProgressDialog
import com.theone.mvvm.entity.ProgressBean
import com.theone.mvvm.ext.qmui.hideLoadingDialog
import com.theone.mvvm.ext.qmui.showFailTipsDialog
import androidx.lifecycle.Observer

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
 * @date 2021-04-30 16:49
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

@SuppressLint("StaticFieldLeak")
private var progressDialog: ProgressDialog? = null

fun Activity.showProgressDialog(data:ProgressBean){
    if(null == progressDialog){
        progressDialog = ProgressDialog(this)
    }
    progressDialog?.run {
        setMessage(data.msg)
        setProgress(data.percent,data.max)
        if(!isShowing){
            show()
        }
    }
}

fun hideProgressDialog(){
    progressDialog?.dismiss()
    progressDialog = null
}

fun Fragment.addFailTipsObserve(vararg vms: BaseRequestViewModel<*>) {
    for (vm in vms) {
        vm.getErrorLiveData().observeInFragment(this, Observer {
            hideLoadingDialog()
            hideProgressDialog()
            showFailTipsDialog(it)
        })
    }
}