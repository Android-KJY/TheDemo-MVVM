package com.theone.mvvm.base.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.theone.mvvm.base.IBaseViewModel
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.ext.getClazz

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
 * @date 2021-03-31 15:01
 * @describe 持有 ViewModel Activity基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseVmActivity<VM : BaseViewModel> : BaseQMUIActivity(), IBaseViewModel<VM> {

    val mViewModel: VM by lazy {
        createViewModel()
    }

    /**
     * ViewModel的位置
     */
    override fun getViewModelIndex(): Int = 0

    /**
     * 创建viewModel
     */
    override fun createViewModel(): VM = ViewModelProvider(this).get(
        getClazz(
            this,
            getViewModelIndex()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 创建观察者
        createObserver()
        initData()
    }

    /**
     * 添加加载观察
     * @param viewModels Array<out BaseViewModel>
     */
    override fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.getShowLoadingLiveData().observeInActivity(this, Observer {
                showLoading(it)
            })
            //关闭弹窗
            viewModel.getHideLoadingLiveData().observeInActivity(this, Observer {
                hideLoading()
            })
            viewModel.getShowProgressLiveData().observeInActivity(this, Observer {
                showProgress(it)
            })
            viewModel.getHideProgressLiveData().observeInActivity(this, Observer {
                hideProgress()
            })
        }
    }

}