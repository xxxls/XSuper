package com.xxxxls.image

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import java.io.File

/**
 *
 * @author Max
 * @date 2020/9/16.
 */

/**
 * 构建Glide请求体
 */
fun Context.buildGlide(
    //网络地址
    url: String? = null,
    //Uri
    uri: Uri? = null,
    //本地文件
    file: File? = null,
    //本地资源
    @DrawableRes resId: Int? = null,
    //bitmap
    bitmap: Bitmap? = null,
    //图片展示方式
    scaleType: ImageView.ScaleType? = ImageView.ScaleType.CENTER_CROP,
    //是否圆角显示
    circle: Boolean = false,
    //圆角大小
    corners: Int = 0,
    //是否跳过内存缓存
    skipMemory: Boolean = false,
    //缓存策略
    diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC,
    //过渡效果
    transitionOptions: TransitionOptions<*, Drawable>? = null,
    //加载资源的宽度
    width: Int = SIZE_ORIGINAL,
    //加载资源的高度
    height: Int = SIZE_ORIGINAL,
    //默认占位图
    @DrawableRes defaultImage: Int? = null,
    //错误占位图
    @DrawableRes errorImage: Int? = defaultImage,
    //监听
    listener: RequestListener<Drawable>? = null
): RequestBuilder<*>? {

    //图片预览方式处理
    val transform =
        when (scaleType) {
            ImageView.ScaleType.FIT_CENTER -> FitCenter()
            ImageView.ScaleType.CENTER_INSIDE -> CenterInside()
            else -> CenterCrop()
        }

    //图片裁剪处理
    val transforms = when {
        circle -> arrayOf(transform, CircleCrop())
        (corners > 0) -> arrayOf(transform, RoundedCorners(corners))
        else -> arrayOf(transform)
    }

    return Glide.with(this).load(
        when {
            !url.isNullOrEmpty() -> url
            uri != null -> uri
            resId != null -> resId
            file != null -> file
            bitmap != null -> bitmap
            else -> null
        }
    )
        //图片裁剪
        .transform(*transforms)
        //图片多渡效果
        .apply {
            transitionOptions?.let {
                this.transition(it)
            }
        }
        //占位图（使用thumbnail替代placeholder，ps:这样可以设置占位图圆角等）
        .thumbnail(transformDrawable(this, defaultImage, transforms))
        //错误图
        .error(transformDrawable(this, errorImage, transforms))
        //跳过内存缓存
        .skipMemoryCache(skipMemory)
        //缓存策略
        .diskCacheStrategy(diskCacheStrategy)
        //加载图片大小
        .override(width, height)
        .listener(listener)
}

/**
 * 加载图片
 */
fun ImageView.loadImage(
    //网络地址
    url: String? = null,
    //Uri
    uri: Uri? = null,
    //本地文件
    file: File? = null,
    //本地资源
    @DrawableRes resId: Int? = null,
    //bitmap
    bitmap: Bitmap? = null,
    //图片展示方式
    scaleType: ImageView.ScaleType? = null,
    //是否圆角显示
    circle: Boolean = false,
    //圆角大小
    corners: Int = 0,
    //是否跳过内存缓存
    skipMemory: Boolean = false,
    //缓存策略
    diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC,
    //加载资源的宽度
    width: Int = SIZE_ORIGINAL,
    //加载资源的高度
    height: Int = SIZE_ORIGINAL,
    //默认占位图
    @DrawableRes defaultImage: Int? = null,
    //错误占位图
    @DrawableRes errorImage: Int? = defaultImage,
    listener: RequestListener<Drawable>? = null
) {
    //页面泄漏处理
    if ((context as? Activity)?.isDestroyed == true) {
        return
    }

    //加载图片至视图
    this.context.buildGlide(
        url = url,
        uri = uri,
        file = file,
        resId = resId,
        bitmap = bitmap,
        transitionOptions = withCrossFade(),
        scaleType = scaleType ?: this.scaleType,
        circle = circle,
        corners = corners,
        skipMemory = skipMemory,
        diskCacheStrategy = diskCacheStrategy,
        width = width,
        height = height,
        defaultImage = defaultImage,
        errorImage = errorImage,
        listener = listener
    )?.into(this)
}


/**
 * 资源转变
 */
private fun transformDrawable(
    context: Context,
    @DrawableRes resId: Int?,
    transformation: Array<out BitmapTransformation>
): RequestBuilder<Drawable>? {
    return Glide.with(context)
        .load(resId)
        .apply(RequestOptions().transform(*transformation))
}