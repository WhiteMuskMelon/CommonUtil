package com.wmm.commonutil.extend

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.wmm.commonutil.R

/**
 * Author: WWM
 * Time: 2021/4/10
 * Description: ImageView 的扩展函数
 */


/**
 * 加载图片
 */
fun ImageView.load(path: String, placeholder: Int = R.mipmap.ic_launcher, useCache: Boolean = false) {
    Glide.with(context).load(path)
            .apply(getOptions(placeholder, useCache))
            .into(this)
}

/**
 * 加载圆角图片
 */
fun ImageView.loadCirclePic(path: String, radius: Int = 20, placeholder: Int = R.mipmap.ic_launcher, useCache: Boolean = false) {
    Glide.with(context).load(path)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
            .apply(getOptions(placeholder, useCache))
            .into(this)
}

/**
 * 取消加载
 */
fun ImageView.clear() {
    Glide.with(context).clear(this)
}

/**
 * 按照图片的宽高比加载
 * 使用本属性的ImageView在xml布局中的width及height属性必须为WRAP_CONTENT
 * widthProportion 为相对于屏幕宽度的比例取值范围为0.0-1.0，当widthProportion=1.0时，ImageView的宽度为屏幕宽度
 * heightProportion为相对于图片宽度的显示比例
 */
fun ImageView.loadWithProportion(widthProportion: Float, heightProportion: Float) {
    adjustViewBounds = true
    val wm: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        wm.defaultDisplay.getRealSize(point)
    } else {
        wm.defaultDisplay.getSize(point)
    }
    val screenWidth: Int = point.x
    val para = this.layoutParams
    para.width = (screenWidth * widthProportion).toInt()
    para.height = ViewGroup.LayoutParams.WRAP_CONTENT
    layoutParams = para
    maxWidth = (screenWidth * widthProportion).toInt()
    maxHeight = (screenWidth * widthProportion * heightProportion).toInt()
    scaleType = ImageView.ScaleType.CENTER_CROP
}

/**
 * 获取设置Glide的参数配置
 */
private fun ImageView.getOptions(placeholder: Int = R.mipmap.ic_launcher, useCache: Boolean = false): RequestOptions {
    return RequestOptions().apply {
        placeholder(placeholder)
        priority(Priority.HIGH)
        if (useCache) diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    }
}