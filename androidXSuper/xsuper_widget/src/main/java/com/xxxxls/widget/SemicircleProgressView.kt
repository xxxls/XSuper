package com.xxxxls.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.xxxxls.utils.UiUtils.dip2px

/**
 * 半圆环形进度视图
 * @author Max
 * @date 2020-01-07.
 */
class SemicircleProgressView : View {

    //文本与圆环的间距
    private var textMargin = dip2px(context,26f)
    //圆环背景路径
    private val ringBackgroundPath = Path()
    //圆环背景画笔
    private val ringBackgroundPaint: Paint by lazy {
        Paint().apply {
            color = Color.GRAY
            strokeWidth = ringWidth
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    //圆环渐变颜色
    private val ringColor: IntArray by lazy {
        IntArray(3).apply {
            this[0] = Color.BLUE
            this[1] = Color.YELLOW
            this[2] = Color.RED
        }
    }

    private val ringShader: Shader by lazy {
        LinearGradient(
            0f, 0f,
            0f, 0f, ringColor, null, Shader.TileMode.MIRROR
        )
    }

    //圆环路径
    private val ringPath = Path()
    //圆环宽度
    private var ringWidth = dip2px(context,6f)
    //圆环画笔
    private val ringPaint: Paint by lazy {
        Paint().apply {
            shader = ringShader
            color = Color.BLACK
            strokeWidth = ringWidth
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    //文本画笔
    private val textPaint: Paint by lazy {
        Paint().apply {
            color = Color.RED
            isAntiAlias = true
            isDither = true
        }
    }

    private val ringPathMeasure: PathMeasure by lazy {
        PathMeasure().apply {
            setPath(ringBackgroundPath, false)
        }
    }

    private val itemPath = Path()
    private val itemPathMeasure: PathMeasure by lazy {
        PathMeasure().apply {
            setPath(itemPath, false)
        }
    }

    //当前进度
    private var progress = 0.35f

    //条目集
    private val items: ArrayList<String> by lazy {
        arrayListOf<String>("V1", "V2", "V3", "V4", "V5", "V6", "V7")
    }


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


    }


}