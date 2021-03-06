package com.theone.mvvm.core.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet
import com.theone.common.constant.BundleConstant
import com.theone.common.ext.getColor
import com.theone.common.ext.getValueNonNull
import com.theone.mvvm.core.R
import com.theone.mvvm.core.base.fragment.BaseImageSnapFragment
import com.theone.mvvm.core.data.entity.ImagePreviewBean
import com.theone.mvvm.core.data.entity.ImagePreviewEvent
import com.theone.mvvm.core.data.entity.QMUIItem
import com.theone.mvvm.core.databinding.BasePullFreshFragmentBinding
import com.theone.mvvm.core.ext.qmui.OnGridBottomSheetItemClickListener
import com.theone.mvvm.core.ext.qmui.showGridBottomSheet
import com.theone.mvvm.core.util.DownloadUtil
import com.theone.mvvm.core.viewmodel.ImagePreviewViewModel
import com.theone.mvvm.core.widge.pullrefresh.PullRefreshLayout
import com.theone.mvvm.ext.qmui.addLeftCloseImageBtn

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
 * @date 2021-04-25 10:18
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
open class ImagePreviewFragment :
    BaseImageSnapFragment<ImagePreviewBean, ImagePreviewViewModel, BasePullFreshFragmentBinding>(),
    OnGridBottomSheetItemClickListener {

    companion object {
        fun newInstance(data: ImagePreviewEvent?): ImagePreviewFragment {
            return ImagePreviewFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BundleConstant.DATA, data)
                }
            }
        }
    }

    private val mData: ImagePreviewEvent by getValueNonNull(BundleConstant.DATA)
    private val mActions = mutableListOf<QMUIItem>()
    protected var mClickData: ImagePreviewBean? = null
    private val TAG_SAVE = "下载"

    override fun translucentFull(): Boolean = true

    override fun isLazyLoadData(): Boolean = false

    override fun initView(root: View) {
        super.initView(root)
        initGridBottomItems(mActions)
        addLeftCloseImageBtn(R.drawable.mz_comment_titlebar_ic_close_light)
        getTopBar()?.run {
            setBackgroundAlpha(0)
            getColor(R.color.white).let {
                setTitle("${mData.position + 1}/${mData.datas.size}").setTextColor(it)
                setSubTitle("").setTextColor(it)
            }
        }
    }

    override fun requestNewData() {
        onFirstLoadSuccess(mData.datas)
        setRefreshLayoutEnabled(false)
        mAdapter.loadMoreModule.loadMoreEnd(true)
        getRecyclerView().scrollToPosition(mData.position)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }


    override fun onImageLongClick(data: ImagePreviewBean, position: Int): Boolean {
        mClickData = data
        mActivity.showGridBottomSheet(mActions, listener = this).show()
        return true
    }

    protected open fun initGridBottomItems(items: MutableList<QMUIItem>) {
        items.add(QMUIItem(TAG_SAVE, R.drawable.svg_operation_save))
    }

    override fun onGridBottomSheetItemClick(dialog: QMUIBottomSheet, itemView: View, tag: String) {
        dialog.dismiss()
        if (tag == TAG_SAVE) {
            mClickData?.let {
                DownloadUtil.startDownload(mActivity, it)
            }
            return
        }
    }

    override fun onScrollChanged(item: ImagePreviewBean, position: Int, total: Int) {
        getTopBar()?.setTitle("${position + 1}/$total")
    }

    override fun getRefreshLayout(): PullRefreshLayout = mBinding.refreshLayout

    override fun getRecyclerView(): RecyclerView = mBinding.recyclerView

    override fun getDataBindingClass(): Class<*> = BasePullFreshFragmentBinding::class.java

}