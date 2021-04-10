package com.wmm.commonutil.utils

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.wmm.commonutil.ctx

/**
 * 日志工具类
 */
object L {

    var isLog: Boolean = true // 是否打印日志，与写日志到本地不冲突
    var logPath: String = "" // 日志存储路径

    private const val defaultTag: String = "test" //默认tag

    @JvmStatic
    fun v(tag: String = defaultTag, msg: String, isWrite: Boolean = false) {
        handleLog({ Log.v(tag, msg) }, isWrite)
    }

    @JvmStatic
    fun d(tag: String = defaultTag, msg: String, isWrite: Boolean = false) {
        handleLog({ Log.d(tag, msg) }, isWrite)
    }

    @JvmStatic
    fun i(tag: String = defaultTag, msg: String, isWrite: Boolean = false) {
        handleLog({ Log.i(tag, msg) }, isWrite)
    }

    @JvmStatic
    fun w(tag: String = defaultTag, msg: String, isWrite: Boolean = false) {
        handleLog({ Log.w(tag, msg) }, isWrite)
    }

    //error的默认写入手机内存
    @JvmStatic
    fun e(tag: String = defaultTag, msg: String, isWrite: Boolean = true) {
        handleLog({ Log.e(tag, msg) }, isWrite)
    }

    //处理是否写文件，是否有权限等
    private fun handleLog(log: () -> Unit, isWrite: Boolean) {
        if (isLog) log()

        //是否有读写权限
        fun isPermissionGranted(): Boolean = ContextCompat.checkSelfPermission(
            ctx,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        //需要存储到手机并且有读写权限
        if (isWrite && isPermissionGranted()) {

        }
    }
}