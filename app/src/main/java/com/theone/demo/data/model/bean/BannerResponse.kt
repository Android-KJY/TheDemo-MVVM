package com.theone.demo.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import com.theone.mvvm.core.callback.IWeb
import kotlinx.android.parcel.Parcelize

/**
 * 轮播图
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class BannerResponse(
    var desc: String = "",
    var id: Int = 0,
    var imagePath: String = "",
    var isVisible: Int = 0,
    var order: Int = 0,
    var title: String = "",
    var type: Int = 0,
    var url: String = ""
) : IWeb, Parcelable {

    override fun getWebUrl(): String = url

    override fun getWebTitle(): String = title

    override fun isWebUrlNeedDecode(): Boolean = false

    override fun isWebTitleNeedChange(): Boolean = false
}


