package com.theone.mvvm.core.net.error

import io.reactivex.functions.Consumer

/**
 * RxJava 错误回调 ,加入网络异常处理
 * User: ljx
 * Date: 2019/04/29
 * Time: 11:15
 */
interface OnError : Consumer<Throwable?> {

    @Throws(Exception::class)
    override fun accept(throwable: Throwable?) {
        onError(ErrorInfo(throwable!!))
    }

    @Throws(Exception::class)
    fun onError(error: ErrorInfo?)

}