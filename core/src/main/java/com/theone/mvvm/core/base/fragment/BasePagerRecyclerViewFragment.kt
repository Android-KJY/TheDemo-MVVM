package com.theone.mvvm.core.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel
import com.theone.mvvm.core.data.enum.LayoutManagerType
import com.theone.mvvm.core.ext.*
import com.theone.mvvm.core.widge.loadsir.callback.ErrorCallback
import com.theone.mvvm.ext.qmui.showInfoTipsDialog

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
 * @date 2021-03-25 10:08
 * @describe RecyclerView基类
 * @email 625805189@qq.com
 * @remark 自行处理Adapter+下拉刷新的请继承此类
 */
abstract class BasePagerRecyclerViewFragment<T, VM : BaseListViewModel<T>, DB : ViewDataBinding> :
    BaseCoreFragment<VM, DB>(), IRecyclerPager<T> {

    /**
     * 由于这个界面需要添加一个泛型T,所以要改变一下ViewModel的位置
     * @return Int
     */
    override fun getViewModelIndex(): Int = 1

    /**
     * 同上
     * @return Int
     */
    override fun getDataBindingIndex(): Int = 2

    /**
     * 获取 RecyclerView.LayoutManager 类型
     * @return LayoutManagerType
     */
    override fun getLayoutManagerType(): LayoutManagerType = LayoutManagerType.LIST

    /**
     * 网格中的列数
     * @return Int
     */
    override fun getSpanCount(): Int = 2

    /**
     * 间距
     * @return Int
     */
    override fun getItemSpace(): Int = 0

    /**
     * 是否需要懒加载数据
     * @return Boolean
     */
    override fun isLazyLoadData(): Boolean = true

    override fun initView(root: View) {
        initAdapter()
        initRecyclerView()
        initRefreshView()
        if (!isLazyLoadData())
            requestNewData()
    }

    protected open fun requestNewData(){
        mViewModel.requestNewData()
    }

    /**
     * 对RRecyclerView进行默认初始化
     * 如需自定义，重写
     */
    override fun initRecyclerView() {
        getRecyclerView().init(getLayoutManager())
    }

    /**
     * 这里默认根据[getLayoutManagerType]提供常见的三种
     * 如有需要，重写此方法返回
     * @return RecyclerView.LayoutManager
     */
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return mActivity.createLayoutManager(getLayoutManagerType(), getSpanCount())
    }

    /**
     * 当再次回到此界面时，自动刷新数据
     * 此时要分两种情况：
     * 1.界面显示空页面或者错误页面，此时需要调用 onFirstLoading() -> 这里面必须加入此方法 showLoadingPage()
     * 2.界面已经存在数据，此时需要调用头部刷新 onRefresh()
     */
    override fun onAutoRefresh() {
        if (getLoadSir()?.currentCallback is ErrorCallback) {
            onFirstLoading()
        } else {
            onRefresh()
        }
    }

    /**
     * 懒加载 - 开始第一次请求数据
     */
    override fun onLazyInit() {
        if (isLazyLoadData())
            onFirstLoading()
    }

    /**
     * 错误、空界面点击事件
     */
    override fun onPageReLoad() {
        mViewModel.isFirst = false
        mViewModel.isFresh = true
        onFirstLoading()
    }

    /**
     * 第一次加载和刷新还是有区别的，所以这里分开
     */
    override fun onFirstLoading() {
        showLoadingPage()
        requestNewData()
    }

    override fun onFirstLoadError(errorMsg: String?) {
        showErrorPage(errorMsg)
        setRefreshLayoutEnabled(false)
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        mViewModel.run {
            isFresh = true
            requestNewData()
        }
    }

    /**
     * 刷新失败
     * @param errorMsg String?
     */
    override fun onRefreshError(errorMsg: String?) {
        showInfoTipsDialog(errorMsg)
        setRefreshLayoutEnabled(true)
    }

    /**
     * 加载更多(page已经在 loadListData 方法里增加了，所以这里只管请求数据）
     * page的更改绝对不能放在这里处理，因为加载更多可能存在失败的情况。
     */
    override fun onLoadMore() {
        mViewModel.requestServer()
    }

    override fun onEmptyData() {
        showEmptyPage()
    }

}