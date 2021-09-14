package com.theone.mvvm.core.base.fragment

import androidx.databinding.ViewDataBinding
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.mvvm.core.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.databinding.BaseFragmentIndexBinding
import net.lucode.hackware.magicindicator.MagicIndicator


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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseTabIndexFragment<VM : BaseViewModel> : BaseTabFragment<VM, BaseFragmentIndexBinding>() {

    override fun showTopBar(): Boolean = false

    override fun getMagicIndicator(): MagicIndicator? = null

    override fun initTabSegment() {
        super.initTabSegment()
        getTabSegment()?.mode = QMUITabSegment.MODE_FIXED
    }

    override fun getDataBindingClass(): Class<*> = BaseFragmentIndexBinding::class.java

    override fun getViewPager(): QMUIViewPager = mBinding.mQMUIViewPager
    override fun getTabSegment(): QMUITabSegment? = mBinding.mTabSegment

}