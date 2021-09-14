package com.theone.mvvm.base.activity

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
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
 * @date 2021-03-31 15:18
 * @describe ViewModel+DataBinding基类
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>(),
    IBaseDataBinding<DB> {

    lateinit var mBinding: DB

    /**
     * 布局ID
     * @return Int
     */
    @Deprecated(message = "DataBinding通过泛型反射进行创建，这个不再需要", replaceWith = ReplaceWith(""))
    override fun getLayoutId(): Int = 0

    /**
     * @return Int  视图里绑定的ViewModel ID
     * @remark 如果使用默认值，则都要命名为 vm ,如果不一致,重写此方法返回
     */
    override fun getBindingVmId(): Int = BR.vm

    /**
     *
     * @return Int 视图里绑定的点击事件 ID
     */
    override fun getBindingClickId(): Int = BR.click

    /**
     * 视图里绑定的点击事件
     * @return Int
     */
    override fun getBindingClick(): Any? = null

    override fun getDataBindingIndex(): Int  = 1

    override fun getDataBindingClass(): Class<*> = getClazz(this,getDataBindingIndex())

    @Suppress("UNCHECKED_CAST")
    override fun createDataBinding(): DB {
        return getDataBindingClass().getDeclaredMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as DB
    }

    override fun createContentView(): View {
        mBinding = createDataBinding()
        mBinding.run {
            lifecycleOwner = this@BaseVmDbActivity
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