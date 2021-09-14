package com.theone.mvvm.base.fragment

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.theone.mvvm.BR
import com.theone.mvvm.base.IBaseDataBinding
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.ext.getClazz
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
 * @date 2021/2/22 0022
 * @describe ViewModel+DataBinding基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>(),
    IBaseDataBinding<DB> {

    lateinit var mBinding: DB

    /**
     * 布局ID
     * @return Int
     */
    @Deprecated(message = "DataBinding通过泛型反射进行创建，这个不再需要", replaceWith = ReplaceWith(""))
    override fun getLayoutId(): Int = 0

    /**
     * 视图里绑定的ViewModel ID
     * @return Int
     */
    override fun getBindingVmId(): Int = BR.vm

    /**
     * 视图里绑定的点击事件 ID
     * @return Int
     */
    override fun getBindingClickId(): Int = BR.click

    /**
     * 视图里绑定的点击事件
     * @return Any?
     */
    override fun getBindingClick(): Any? = null

    /**
     * DataBinding在泛型中的位置
     * @return Int
     * @remark 如果子类中没有了DB泛型，则需要重写[getDataBindingClass]直接指定Class
     *         例：很多界面都用的是一个DB，那么可以指定这个DB进行封装，子类重写后是没有DB的
     */
    override fun getDataBindingIndex(): Int = 1

    /**
     * 生成DataBinding的class
     * @return Class<*>
     */
    override fun getDataBindingClass(): Class<*> = getClazz(this,getDataBindingIndex())

    /**
     * 创建DataBinding
     * @return DB
     */
    @Suppress("UNCHECKED_CAST")
    override fun createDataBinding(): DB {
        return getDataBindingClass().getDeclaredMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as DB
    }

    /**
     * 创建内容层 - 这一层使用DataBinding
     * @return View
     */
    override fun createContentView(): View {
        mBinding = createDataBinding()
        mBinding.run {
            lifecycleOwner = this@BaseVmDbFragment
            setVariable(getBindingVmId(), mViewModel)
            getBindingClick()?.let {
                setVariable(getBindingClickId(), it)
            }
            SparseArray<Any>().apply {
                createBindingParams(this)
                forEach { key, any ->
                    setVariable(key, any)
                }
            }.clear()
        }
        return mBinding.root
    }

}