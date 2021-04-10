package com.wmm.commonutil.utils

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.wmm.commonutil.ctx

/**
 * App相关工具类
 * 获取app版本名字
 */
object AppUtil {

    private val packageManager: PackageManager = ctx.packageManager
    private val packageInfo: PackageInfo = packageManager.getPackageInfo(ctx.packageName, 0)

    //获取app版本名字
    fun getVersionName(): String {
        return try {
            packageInfo.versionName
        } catch (e: Exception) {
            L.e("AppUtil", "获取版本名字出错 $e")
            ""
        }
    }

    //获取app版本号
    fun getVersionCode(): Int {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toInt()
            } else {
                packageInfo.versionCode
            }
        } catch (e: Exception) {
            L.e("AppUtil", "获取版本号出错 $e")
            0
        }
    }

    //获取该app名字
    fun getAPPName(): String {
        val appResID: Int = packageInfo.applicationInfo.labelRes
        return ctx.resources.getString(appResID)
    }

    //获取该app包名
    fun getPackageName(): String = packageInfo.packageName
}