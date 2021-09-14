package com.theone.mvvm.core.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.ViewDataBinding
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.common.ext.*
import com.theone.mvvm.core.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.databinding.BaseTabInTitleLayoutBinding
import net.lucode.hackware.magicindicator.MagicIndicator
import java.lang.reflect.ParameterizedType


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
 * @date 2021/3/3 0003
 * @describe Tab在TitleBar类型
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseTabInTitleFragment<VM : BaseViewModel> :
    BaseTabFragment<VM, BaseTabInTitleLayoutBinding>() {

    private val mMagicIndicator: MagicIndicator by lazy {
        MagicIndicator(context).apply {
            layoutParams = RelativeLayout.LayoutParams(wrapContent, matchParent).apply {
                addRule(RelativeLayout.CENTER_IN_PARENT)
            }
        }
    }

    override fun showTopBar(): Boolean = true

    override fun initView(root: View) {
        // 这个一定要放在前面，Tab不是从网络获取时startInit可能会先执行。
        initTopBar()
        super.initView(root)
    }

    protected open fun initTopBar() {
        getTopBar()?.run {
            setCenterView(mMagicIndicator)
            // 如果数据从网络获取先把TopBar隐藏掉。【能用INVISIBLE就不要用GONE】
            if (null == getRequestViewModel())
                invisible()
        }
    }

    override fun getDataBindingClass(): Class<*> = BaseTabInTitleLayoutBinding::class.java

    override fun getMagicIndicator(): MagicIndicator? = mMagicIndicator

    override fun getViewPager(): QMUIViewPager = mBinding.mQMUIViewPager

    override fun getTabSegment(): QMUITabSegment? = null

}