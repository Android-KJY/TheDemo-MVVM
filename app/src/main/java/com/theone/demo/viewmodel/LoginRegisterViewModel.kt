package com.theone.demo.viewmodel

import com.theone.demo.data.model.bean.UserInfo
import com.theone.demo.data.repository.ApiRepository
import com.theone.mvvm.core.ext.request
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.callback.databind.BooleanObservableField
import com.theone.mvvm.callback.databind.StringObservableField

class LoginRegisterViewModel : BaseRequestViewModel<UserInfo>() {

    var account = StringObservableField()
    var password = StringObservableField()
    var repassword = StringObservableField()

    var isRegister = BooleanObservableField()

    override fun requestServer() {
        request({
            onSuccess(ApiRepository().loginOrRegister(account.get(),password.get(),repassword.get(),isRegister.get()))
        }, if (isRegister.get()) "注册中" else "登录中")
    }

}