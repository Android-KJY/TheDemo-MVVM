package com.theone.demo.viewmodel.request

import com.theone.demo.data.model.bean.IntegralResponse
import com.theone.demo.data.repository.ApiRepository
import com.theone.demo.viewmodel.getCacheMode
import com.theone.mvvm.core.ext.request
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
import com.theone.mvvm.callback.databind.BooleanObservableField


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
 * @date 2021/3/4 0004
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class MineRequestViewModel : BaseRequestViewModel<IntegralResponse>() {

    var isFirst =  BooleanObservableField(true)

    override fun requestServer() {
        request({
            onSuccess(ApiRepository.INSTANCE.getUserCoin(getCacheMode(isFirst.get())))
        })
    }

}