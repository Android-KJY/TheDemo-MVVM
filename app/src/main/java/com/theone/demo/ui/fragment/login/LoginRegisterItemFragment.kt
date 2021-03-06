package com.theone.demo.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.theone.common.constant.BundleConstant.TYPE
import com.theone.common.ext.bundle
import com.theone.common.ext.getValueNonNull
import com.theone.demo.R
import com.theone.demo.app.util.CacheUtil
import com.theone.demo.viewmodel.LoginRegisterViewModel
import com.theone.demo.databinding.FragmentLoginRegisterBinding
import com.theone.demo.viewmodel.AppViewModel
import com.theone.mvvm.ext.getAppViewModel
import com.theone.mvvm.core.base.fragment.BaseCoreFragment
import com.theone.mvvm.ext.qmui.showFailTipsDialog
import com.theone.mvvm.ext.qmui.showSuccessTipsExitDialog

class LoginRegisterItemFragment private constructor():
    BaseCoreFragment<LoginRegisterViewModel, FragmentLoginRegisterBinding>() {

    companion object {
        fun newInstant(isRegister: Boolean): LoginRegisterItemFragment {
           return LoginRegisterItemFragment().bundle {
                putBoolean(TYPE, isRegister)
            }
        }
    }

    val mAppVm: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    private val isRegister :Boolean by getValueNonNull(TYPE)

    override fun getBindingClick(): Any?  = ProxyClick()

    override fun initView(root: View) {
        mViewModel.isRegister.set(isRegister)
    }

    override fun createObserver() {
        mViewModel.run {
            getResponseLiveData().observeInFragment(this@LoginRegisterItemFragment, Observer {
                mAppVm.userInfo.value = it
                CacheUtil.setUser(it)
                showSuccessTipsExitDialog(if (isRegister.get()) "注册" else "登录"+"成功")
            })
            getErrorLiveData().observeInFragment(this@LoginRegisterItemFragment, Observer {
                showFailTipsDialog(it)
            })
        }

    }


    inner class ProxyClick{

        fun login() {
            when {
                mViewModel.account.get().isEmpty() -> showFailTipsDialog("请填写账号")
                mViewModel.password.get().isEmpty() -> showFailTipsDialog("请填写密码")
                mViewModel.isRegister.get() && mViewModel.repassword.get()
                    .isEmpty() -> showFailTipsDialog("请填写确认密码")
                else -> {
                    mViewModel.requestServer()
                }
            }
        }
    }

}