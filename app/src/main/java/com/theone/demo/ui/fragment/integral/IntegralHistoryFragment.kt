package com.theone.demo.ui.fragment.integral

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.theone.demo.data.model.bean.IntegralHistoryResponse
import com.theone.demo.ui.adapter.IntegralHistoryAdapter
import com.theone.demo.ui.fragment.base.BasePagerDbFragment
import com.theone.demo.ui.fragment.base.BasePagerListFragment
import com.theone.demo.viewmodel.IntegralHistoryViewModel
import com.theone.mvvm.core.databinding.BaseRecyclerPagerFragmentBinding
import com.theone.mvvm.ext.qmui.setTitleWitchBackBtn


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
 * @date 2021/3/17 0017
 * @describe 积分记录
 * @email 625805189@qq.com
 * @remark
 */
class IntegralHistoryFragment:
    BasePagerDbFragment<IntegralHistoryResponse, IntegralHistoryViewModel>() {

    override fun createAdapter(): BaseQuickAdapter<IntegralHistoryResponse, *> = IntegralHistoryAdapter()

    override fun initView(root: View) {
        super.initView(root)
        setTitleWitchBackBtn("积分记录")
    }

    override fun initData() {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }

}