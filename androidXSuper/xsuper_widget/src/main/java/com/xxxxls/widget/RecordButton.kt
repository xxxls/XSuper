package com.xxxxls.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.util.*
import kotlin.math.min


/**
 * 录制视频控件
 * @author Max
 * @date 2020/4/14.
 */
class RecordButton : View {

    var onProgressChangeListener: OnProgressChangeListener? = null

    var onRecordStateChangedListener: OnRecordStateChangedListener? = null

    // 分段进度记录
    private val splitList = ArrayList<Float>()

    // 当前进度
    private var progress: Float = 0f

    // 标记进度点
//    private var tagProgress: Float? = null

    // 进度环边框宽度
    private var progressRingStrokeWidth: Float = 10f

    // 进度环半径
    private var progressRingRadius: Float = 100f

    // 内块最小半径
    private var circleMinRadius: Float = 30f

    // 内块最大半径
    private var circleMaxRadius: Float = 100f

    // 内块半径
    private var circleRadius: Float = circleMaxRadius

    // 进度分割点所占角度
    private var progressSplitAngle = 0.002f

    // 进度环背景颜色值
    private lateinit var progressRingBackgroundColor: IntArray

    // 进度环颜色值
    private lateinit var progressRingColor: IntArray

    // 内块颜色值
    private lateinit var circleColor: IntArray

    // 进度环分段颜色值
    private var progressRingSplitColor: Int = Color.WHITE

    // 进度环分段宽度
    private var progressRingSplitWidth: Float = 6f

    // 进度环背景着色器
    private val progressRingBackgroundShader: Shader by lazy {
        LinearGradient(
            centerPoint.x - progressRingRadius,
            0f,
            centerPoint.x + progressRingRadius,
            0f,
            progressRingBackgroundColor,
            null,
            Shader.TileMode.CLAMP
        )
    }

    // 进度环着色器
    private val progressRingShader: Shader by lazy {
        LinearGradient(
            centerPoint.x - progressRingRadius,
            0f,
            centerPoint.x + progressRingRadius,
            0f,
            progressRingColor,
            null,
            Shader.TileMode.CLAMP
        )
    }

    // 内块着色器
    private val circleShader: Shader by lazy {
        LinearGradient(
            centerPoint.x - circleMaxRadius,
            0f,
            centerPoint.x + circleMaxRadius,
            0f,
            circleColor,
            null,
            Shader.TileMode.CLAMP
        )
    }

    // 中心点
    private val centerPoint: PointF by lazy {
        PointF()
    }

    // 进度环画笔
    private val progressRingPaint: Paint by lazy {
        Paint().apply {
            shader = progressRingShader
            strokeWidth = progressRingStrokeWidth
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
        }
    }

    // 进度环分段点画笔
    private val progressRingSplitPaint: Paint by lazy {
        Paint().apply {
            color = progressRingSplitColor
            strokeWidth = progressRingStrokeWidth
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
        }
    }

    // 外环进度位置信息
    private val progressRingRect: RectF by lazy {
        RectF(
            centerPoint.x - progressRingRadius,
            centerPoint.y - progressRingRadius,
            centerPoint.x + progressRingRadius,
            centerPoint.y + progressRingRadius
        )
    }

    // 重复模式
    private var recordMode: RecordMode = RecordMode.ORIGIN

    // 内块画笔
    private val circlePaint: Paint by lazy {
        Paint().apply {
            shader = circleShader
            strokeWidth = progressRingStrokeWidth
            isAntiAlias = true
            isDither = true
            style = Paint.Style.FILL
        }
    }

    // 内块动画
    private var circleRadiusAnimator: ObjectAnimator? = null

    private var lastDownEventTime = System.currentTimeMillis()

    private val clickRunnable: Runnable by lazy {
        Runnable {
            recordMode = RecordMode.LONG_CLICK
        }
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr, 0
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.RecordButton)

        val progressRingBackgroundStartColor = typedArray?.getColor(
            R.styleable.RecordButton_r_progress_ring_background_start_color,
            Color.GRAY
        ) ?: Color.GRAY
        val progressRingBackgroundEndColor = typedArray?.getColor(
            R.styleable.RecordButton_r_progress_ring_background_end_color,
            progressRingBackgroundStartColor
        ) ?: progressRingBackgroundStartColor
        progressRingBackgroundColor = IntArray(2).apply {
            this[0] = progressRingBackgroundStartColor
            this[1] = progressRingBackgroundEndColor
        }

        val progressRingStartColor = typedArray?.getColor(
            R.styleable.RecordButton_r_progress_ring_start_color,
            Color.RED
        ) ?: Color.RED
        val progressRingEndColor = typedArray?.getColor(
            R.styleable.RecordButton_r_progress_ring_end_color,
            progressRingStartColor
        ) ?: progressRingStartColor
        progressRingColor = IntArray(2).apply {
            this[0] = progressRingStartColor
            this[1] = progressRingEndColor
        }

        progressRingStrokeWidth = typedArray?.getDimension(
            R.styleable.RecordButton_r_progress_ring_stroke_width,
            progressRingStrokeWidth
        )
            ?: progressRingStrokeWidth

        progressRingSplitColor =
            typedArray?.getColor(R.styleable.RecordButton_r_split_color, progressRingSplitColor)
                ?: progressRingSplitColor

        progressRingSplitWidth =
            typedArray?.getDimension(R.styleable.RecordButton_r_split_width, progressRingSplitWidth)
                ?: progressRingSplitWidth

        val circleStartColor = typedArray?.getColor(
            R.styleable.RecordButton_r_circle_start_color,
            Color.YELLOW
        ) ?: Color.YELLOW
        val circleEndColor = typedArray?.getColor(
            R.styleable.RecordButton_r_circle_end_color,
            circleStartColor
        ) ?: circleStartColor
        circleColor = IntArray(2).apply {
            this[0] = circleStartColor
            this[1] = circleEndColor
        }
        circleMaxRadius =
            typedArray?.getDimension(R.styleable.RecordButton_r_circle_max_radius, 0f) ?: 0f

        circleMinRadius =
            typedArray?.getDimension(R.styleable.RecordButton_r_circle_min_radius, 0f) ?: 0f
        typedArray?.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerPoint.x = w / 2f
        centerPoint.y = h / 2f
        progressRingRadius = (min(w.toFloat(), h.toFloat()) - progressRingStrokeWidth) / 2
        if (circleMaxRadius == 0f) {
            circleMaxRadius = progressRingRadius * 0.8f
        }
        if (circleMinRadius == 0f) {
            circleMinRadius = progressRingRadius * 0.3f
        }

        progressSplitAngle =
            360 * (progressRingSplitWidth / (2 * Math.PI * progressRingRadius)).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {

            // 内圆
            save()
            val scaleRate = circleRadius / circleMaxRadius
            scale(scaleRate, scaleRate, centerPoint.x, centerPoint.y)
            drawCircle(centerPoint.x, centerPoint.y, circleMaxRadius, circlePaint)
            restore()

            // 背景外环
            progressRingPaint.shader = progressRingBackgroundShader
            drawCircle(centerPoint.x, centerPoint.y, progressRingRadius, progressRingPaint)

            // 外环进度
            progressRingPaint.shader = progressRingShader
            drawArc(
                progressRingRect,
                -90f,
                progress * 360f,
                false,
                progressRingPaint
            )

            // 外环进度断点
            progressRingPaint.shader = null
            progressRingPaint.color = progressRingSplitColor

            // 外环分段点
            splitList.forEach {
                drawArc(
                    progressRingRect,
                    it * 360f - progressSplitAngle / 2 - 90,
                    progressSplitAngle,
                    false,
                    progressRingSplitPaint
                )
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!isNormalEvent()) {
                    return false
                }
                if (recordMode == RecordMode.ORIGIN) {
                    handler.postDelayed(clickRunnable, 200)
                    start()
                }
            }

            MotionEvent.ACTION_UP -> {
                when (recordMode) {
                    RecordMode.ORIGIN -> {
                        recordMode = RecordMode.SINGLE_CLICK
                        handler.removeCallbacks(clickRunnable)
                    }
                    RecordMode.LONG_CLICK -> {
                        stop()
                    }
                    RecordMode.SINGLE_CLICK -> {
                        stop()
                    }
                }
            }
        }
        return true
    }

    // 防止疯狂点击
    private fun isNormalEvent(): Boolean {
        val time = System.currentTimeMillis()
        if (time - lastDownEventTime < 500) {
            return false
        }
        lastDownEventTime = time
        return true
    }

    /**
     * 开始
     */
    fun start() {
        startBeginAnimation()
        onRecordStateChangedListener?.onRecordStart()
    }

    /**
     * 停止
     */
    fun stop() {
        handler.removeCallbacks(clickRunnable)
        recordMode = RecordMode.ORIGIN
        onRecordStateChangedListener?.onRecordStop()
        startEndAnimation()
    }

    private fun stopAnimation() {
        circleRadiusAnimator?.cancel()
    }

    /**
     * 开始动画
     */
    private fun startBeginAnimation() {
        stopAnimation()
        circleRadiusAnimator = ObjectAnimator.ofFloat(
            this, "circleRadius",
            circleMaxRadius, circleMinRadius
        ).apply {
            this.duration = 500
        }
        circleRadiusAnimator?.start()
    }

    /**
     * 结束动画
     */
    private fun startEndAnimation() {
        stopAnimation()
        circleRadiusAnimator = ObjectAnimator.ofFloat(
            this, "circleRadius",
            circleRadius, circleMaxRadius
        ).apply {
            this.duration = 500
        }
        circleRadiusAnimator?.start()
    }

    /**
     * 清除所有分割点
     */
    fun clearSplit() {
        splitList.clear()
        invalidate()
    }

    /**
     * 设置当前点为分割点
     */
    fun addSplit() {
        splitList.add(progress)
        invalidate()
    }

    /**
     * 删除最后一个分割点
     */
    fun removeLastSplit() {
        if (splitList.size > 0) {
            progress = splitList[splitList.size - 1]
            splitList.remove(progress)
            progress = if (splitList.size > 0) {
                splitList[splitList.size - 1]
            } else {
                0f
            }
            invalidate()
        }
        onChange()
    }

    /**
     * 设置进度
     */
    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    fun getProgress(): Float {
        return progress
    }

    private fun setCircleRadius(radius: Float) {
        this.circleRadius = radius
        invalidate()
    }

    /**
     * 改变事件
     */
    private fun onChange() {
        onProgressChangeListener?.onChange(progress)
    }


    private fun log(message: String) {
        Log.e("RecordView", message)
    }

    interface OnProgressChangeListener {
        /**
         * 进度改变
         */
        fun onChange(progress: Float)
    }

    interface OnRecordStateChangedListener {
        /**
         * 开始录制
         */
        fun onRecordStart()

        /**
         * 结束录制
         */
        fun onRecordStop()
    }

    private enum class RecordMode {
        /**
         * 单击录制模式
         */
        SINGLE_CLICK,
        /**
         * 长按录制模式
         */
        LONG_CLICK,
        /**
         * 初始化
         */
        ORIGIN
    }

}