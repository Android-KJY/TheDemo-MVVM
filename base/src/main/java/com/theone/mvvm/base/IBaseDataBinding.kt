package com.theone.mvvm.base

import android.util.SparseArray
import androidx.annotation.NonNull

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
 * @date 2021-03-31 15:04
 * @describe DataBinding基类相关
 * @email 625805189@qq.com
 * @remark
 */
interface IBaseDataBinding<DB> {

    /**
     * DataBinding在泛型中的位置
     * @return Int
     * @remark 如果子类中没有了DB泛型，则需要重写[getDataBindingClass]直接指定Class
     *         例：很多界面都用的是一个DB，那么可以指定这个DB进行封装，子类重写后是没有DB的
     */
    fun getDataBindingIndex():Int

    /**
     * 生成DataBinding的class
     * @return Class<*>
     */
    fun getDataBindingClass():Class<*>

    /**
     * 创建DataBinding
     * @return DB
     */
    fun createDataBinding():DB

    /**
     * 视图绑定里ViewModel的ID
     * @return Int
     */
    fun getBindingVmId():Int

    /**
     * 视图绑定里Click的ID
     * @return Int
     */
    fun getBindingClickId(): Int

    /**
     * 视图绑定里的Click
     * @return Any?
     */
    fun getBindingClick(): Any?

    fun createBindingParams(bindingParams: SparseArray<Any>)

}
