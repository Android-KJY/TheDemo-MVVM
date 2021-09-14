package com.theone.mvvm.core.base.fragment

import androidx.databinding.ViewDataBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel


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
 * @date 2021/2/23 0023
 * @describe RecyclerView分页显示基类
 * @email 625805189@qq.com
 * @remark 给定了默认的下拉刷新控件 SwipeRefreshLayout
 */
abstract class BasePagerSwipeRefreshFragment
<T, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BasePagerAdapterFragment<T, VM, DB>(),
    SwipeRefreshLayout.OnRefreshListener {

    abstract fun getRefreshLayout(): SwipeRefreshLayout?

    /**
     * 初始化下拉刷新
     */
    override fun initRefreshView() {
        getRefreshLayout()?.run {
            isEnabled = false
            setOnRefreshListener(this@BasePagerSwipeRefreshFragment)
        }
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        getRefreshLayout()?.isRefreshing = true
        super.onRefresh()
    }

    override fun setRefreshLayoutEnabled(enabled: Boolean) {
        getRefreshLayout()?.run {
            isEnabled = enabled
            isRefreshing = false
        }
    }

}