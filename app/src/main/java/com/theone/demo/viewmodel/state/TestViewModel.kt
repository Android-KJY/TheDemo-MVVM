package com.theone.demo.viewmodel.state

import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.callback.databind.StringObservableField

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
 * @date 2021-03-30 17:10
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TestViewModel:BaseViewModel() {

    val cover = StringObservableField("https://p.pstatp.com/origin/pgc-image/bf0e0e16e8e44c62a3318ab9bb12fbb7")

}