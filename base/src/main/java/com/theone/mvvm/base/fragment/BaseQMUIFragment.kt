package com.theone.mvvm.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.util.QMUIKeyboardHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.mvvm.base.IBaseQMUI
import com.theone.mvvm.ext.createTopBar
import com.theone.mvvm.ext.createView
import com.theone.mvvm.ext.qmui.*


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
 * @describe BaseFragment
 * @email 625805189@qq.com
 * @remark 懒加载+TopBar+状态栏等的封装
 */

abstract class BaseQMUIFragment : QMUIFragment(), IBaseQMUI, LifecycleObserver {

    protected val TAG: String = this.javaClass.simpleName

    lateinit var mActivity: AppCompatActivity

    /**
     * 是否为根Fragment： getParentFragment() == null
     * 可作为一些默认情况的判断依据
     */
    private var isIndexFragment = false

    /**
     * 是否第一次加载
     */
    private var mIsFirstLayInit = true

    /**
     * 这个方法是为了给BaseVmDbFragment重写绑定视图的
     * 所以仅供此包内使用
     */
    internal open fun createContentView(): View = layoutInflater.inflate(getLayoutId(), null)

    /**
     * 内容层
     */
    private val mContent: View by lazy {
        createContentView()
    }

    /**
     * 提供一个方法子类获取内容层
     * @return View
     */
    override fun getContentView(): View = mContent

    /**
     * 是否需要TopBar(默认为根Fragment才需要)
     * 子类重写此方法进行修改
     */
    override fun showTopBar(): Boolean = isIndexFragment

    /**
     * TopBar
     */
    private val mTopBar: QMUITopBarLayout? by lazy {
        createTopBar(mActivity)
    }

    /**
     * 提供一个方法供子类获取TopBar
     */
    override fun getTopBar(): QMUITopBarLayout? = mTopBar

    /**
     * true -> 内容层将充满整个屏幕，直接延伸至状态栏
     *
     * false ->内容层将有一个向上的TopBar高度的间距
     */
    override fun translucentFull(): Boolean = false

    override fun onCreateView(): View = createView(mActivity, translucentFull())

    override fun onViewCreated(rootView: View) {
        mIsFirstLayInit = true
        initView(rootView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyViewLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
        isIndexFragment = null == parentFragment
    }

    /**
     * 显示加载框
     * @param msg String? 提示语
     * @remark 这了提供了默认的加载效果，如果需要更改，重写此方法以及[hideLoading]
     */
    override fun showLoading(msg: String?) {
        context?.showLoadingDialog(msg)
    }

    /**
     * 隐藏加载框
     */
    override fun hideLoading() {
        hideLoadingDialog()
    }

    /**
     * 懒加载分两种情况
     * 1.在动画结束后开始进行加载
     * 2.当前Fragment为子Fragment时，比如ViewPager的ItemFragment,或者点击切换的，这种情况下当界面可见时才进行加载
     *
     * 这里自动根据 [isIndexFragment] 判断是以哪种情况进行懒加载
     */
    abstract fun onLazyInit()

    /**
     * 界面可见时
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onLazyResume() {
        if (isNeedChangeStatusBarMode()) {
            updateStatusBarMode(isStatusBarLightMode())
        }
        checkLazyInit(!isIndexFragment)
    }

    /**
     * 动画结束时
     * @param animation Animation?
     */
    override fun onEnterAnimationEnd(animation: Animation?) {
        super.onEnterAnimationEnd(animation)
        checkLazyInit(isIndexFragment)
    }

    /**
     * 检查是否需要延迟初始化
     * @param check Boolean
     */
    private fun checkLazyInit(check: Boolean) {
        if (check&&mIsFirstLayInit) {
            view?.post {
                mIsFirstLayInit = false
                onLazyInit()
            }
        }
    }

    /**
     * @return 是否要进行对状态栏的处理
     * @remark 默认当为根fragment时才进行处理
     */
    override fun isNeedChangeStatusBarMode(): Boolean = isIndexFragment

    /**
     * @return 是否设置状态栏LightMode true 深色图标 false 白色背景
     * @remark 根据自己APP的配色，给定一个全局的默认模式。
     *         建议用TopBar的背景颜色做判断。或者在自己的BaseFragment里提供一个全局默认的模式。
     */
    override fun isStatusBarLightMode(): Boolean = true

    /**
     * 向外提供的关闭方法
     */
    open fun finish() {
        // 这里一定要用这个方法
        onBackPressed()
        // 不能用这个
        // popBackStack()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onLazyPause() {
        QMUIKeyboardHelper.hideKeyboard(view)
    }

}