package com.theone.demo.ui.fragment.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
import com.theone.mvvm.core.databinding.BaseSwipeRefreshFragmentBinding

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
 * @date 2021-08-09 15:43
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BasePagerDbFragment<T, VM : BaseListViewModel<T>>:BasePagerListFragment<T,VM, BaseSwipeRefreshFragmentBinding>() {

    override fun getDataBindingClass(): Class<*>  = BaseSwipeRefreshFragmentBinding::class.java

    override fun getRecyclerView(): RecyclerView = mBinding.recyclerView

    override fun getRefreshLayout(): SwipeRefreshLayout? = mBinding.swipeRefresh

}