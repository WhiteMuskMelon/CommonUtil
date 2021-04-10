package com.wmm.commonutil.utils

import android.os.Looper
import android.widget.Toast
import com.wmm.commonutil.ctx

/**
 * Toast工具类
 */

object T {

    private var toast: Toast? = null
    var isAddAPPName: Boolean = false //是否在Toast前加上应用名称

    //长时间提示
    @JvmStatic
    fun showLong(msg: String) {
        handleToast(msg, Toast.LENGTH_LONG)
    }

    //短时间提示
    @JvmStatic
    fun showShort(msg: String) {
        handleToast(msg, Toast.LENGTH_SHORT)
    }

    //提示处理
    private fun handleToast(msg: String, time: Int) {
        //如果上次还再显示，就取消上次显示，要弹出新的
        toast?.cancel()

        //设置toast
        fun setToast() {
            toast = Toast.makeText(ctx, msg, time)
            toast?.run {
                if (!isAddAPPName) setText(msg)// 处理toast最前端加应用名字
                show()
            }
        }

        //弹出Toast不一定非得在主线程，子线程只要提供looper也可以
        if (Looper.myLooper() == null) {
            //子线程
            Looper.prepare()
            setToast()
            Looper.loop()
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            //主线程
            setToast()
        }
    }
}