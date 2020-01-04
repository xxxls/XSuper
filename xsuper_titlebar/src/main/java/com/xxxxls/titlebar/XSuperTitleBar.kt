package com.xxxxls.titlebar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import com.xxxxls.titlebar.utils.getDrawableById

/**
 * TitleBar
 * @author Max
 * @date 2020-01-03.
 */
open class XSuperTitleBar : FrameLayout, View.OnClickListener {

    companion object {

        //当前标题栏样式
        private var titleBarStyle: ITitleBarStyle? = null

        /**
         * 全局设置标题栏样式
         */
        fun initStyle(style: ITitleBarStyle?) {
            titleBarStyle = style
        }
    }

    //主标题与副标题之间的间距（两个都有内容时才生效）
    protected open var titleMargin: Int = 0

    //子条目内间距（左右）
    protected open var childLeftRightPadding: Int = 0

    //标题点击事件
    var onTitleClickListener: OnTitleClickListener? = null

    //标题点击事件
    var onSubTitleClickListener: OnSubTitleClickListener? = null

    //左标题点击事件
    var onLeftTitleClickListener: OnLeftTitleClickListener? = null

    //右标题点击事件
    var onRightTitleClickListener: OnRightTitleClickListener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val array = getContext().obtainStyledAttributes(attrs, R.styleable.XSuperTitleBar)

        //标题间距
        titleMargin =
            array.getDimensionPixelOffset(
                R.styleable.XSuperTitleBar_titlebar_title_margin,
                getTitleBarStyle().getTitleMargin()
            )

        //子条目左右内间距
        childLeftRightPadding = array.getDimensionPixelOffset(
            R.styleable.XSuperTitleBar_titlebar_child_left_right_padding,
            getTitleBarStyle().getChildLeftRightPadding()
        )

        //初始化左视图
        initLeftView(array)
        //初始化右视图
        initRightView(array)
        //初始化标题视图（主，副标题）
        initTitleLayout(array)
        //初始化分割线
        initLineView(array)
        array.recycle()
    }

    /**
     * 初始化左view
     */
    private fun initLeftView(array: TypedArray) {
        //文本
        val text = array.getString(R.styleable.XSuperTitleBar_titlebar_left_text)
        //图标
        val icon = array.getDrawable(R.styleable.XSuperTitleBar_titlebar_left_icon)
        //文本大小
        val textSize =
            array.getDimension(
                R.styleable.XSuperTitleBar_titlebar_left_text_size,
                getTitleBarStyle().getLeftTextSize()
            )
        //文本颜色
        val textColor =
            array.getColor(
                R.styleable.XSuperTitleBar_titlebar_left_text_color,
                getTitleBarStyle().getLeftTextColor()
            )
        //文本与图标间距
        val drawablePadding = array.getDimension(
            R.styleable.XSuperTitleBar_titlebar_left_drawable_padding,
            getTitleBarStyle().getDrawablePadding()
        )

        val itemView = AppCompatTextView(context).apply {
            id = R.id.titlebar_left_view
            isSingleLine = true
            gravity = Gravity.CENTER
            ellipsize = TextUtils.TruncateAt.END
            setTextSize(COMPLEX_UNIT_PX, textSize)
            setTextColor(textColor)
            compoundDrawablePadding = drawablePadding.toInt()
            setOnClickListener(this@XSuperTitleBar)
            setPadding(childLeftRightPadding, 0, childLeftRightPadding, 0)
        }

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        ).apply {
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
        }

        this.addView(itemView, layoutParams)

        //设置标题
        setLeftText(text)
        //设置图标
        icon?.let {
            setLeftIcon(it)
        } ?: setLeftIcon(getTitleBarStyle().getLeftIcon())
    }


    /**
     * 初始化右view
     */
    private fun initRightView(array: TypedArray) {

        val text = array.getString(R.styleable.XSuperTitleBar_titlebar_right_text)
        val icon = array.getDrawable(R.styleable.XSuperTitleBar_titlebar_right_icon)
        val textSize =
            array.getDimension(
                R.styleable.XSuperTitleBar_titlebar_right_text_size,
                getTitleBarStyle().getRightTextSize()
            )
        val textColor =
            array.getColor(
                R.styleable.XSuperTitleBar_titlebar_right_text_color,
                getTitleBarStyle().getRightTextColor()
            )
        val drawablePadding = array.getDimension(
            R.styleable.XSuperTitleBar_titlebar_right_drawable_padding,
            getTitleBarStyle().getDrawablePadding()
        )

        val itemView = AppCompatTextView(context).apply {
            id = R.id.titlebar_right_view
            isSingleLine = true
            gravity = Gravity.CENTER
            ellipsize = TextUtils.TruncateAt.END
            setTextSize(COMPLEX_UNIT_PX, textSize)
            setTextColor(textColor)
            compoundDrawablePadding = drawablePadding.toInt()
            setOnClickListener(this@XSuperTitleBar)
            setPadding(childLeftRightPadding, 0, childLeftRightPadding, 0)
        }

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        ).apply {
            gravity = Gravity.END or Gravity.CENTER_VERTICAL
        }

        this.addView(itemView, layoutParams)

        //设置标题
        setRightText(text)
        //设置图标
        setRightIcon(icon)
    }

    /**
     * 初始化标题（主标题，副标题）
     */
    private fun initTitleLayout(array: TypedArray) {
        //两个标题的容器
        val titleLayout = LinearLayout(context).apply {
            id = R.id.titlebar_title_layout
            orientation = LinearLayout.VERTICAL
        }

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }
        this.addView(titleLayout, layoutParams)

        //初始化主标题
        initTitleView(titleLayout, array = array)
        //初始化副标题
        initSubTitleView(titleLayout, array = array)
    }

    /**
     * 初始化标题
     **/
    private fun initTitleView(parent: LinearLayout, array: TypedArray) {

        val text = array.getString(R.styleable.XSuperTitleBar_titlebar_title)
        val textSize =
            array.getDimension(
                R.styleable.XSuperTitleBar_titlebar_title_text_size,
                getTitleBarStyle().getTitleTextSize()
            )
        val textColor =
            array.getColor(
                R.styleable.XSuperTitleBar_titlebar_title_text_color,
                getTitleBarStyle().getTitleTextColor()
            )

        //主标题
        val itemView = AppCompatTextView(context).apply {
            id = R.id.titlebar_title_view
            gravity = Gravity.CENTER
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.END
            setTextSize(COMPLEX_UNIT_PX, textSize)
            setTextColor(textColor)
            setOnClickListener(this@XSuperTitleBar)
        }

        val layoutParams = LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }

        parent.addView(itemView, layoutParams)

        //设置标题
        setTitleText(text)
    }

    /**
     * 初始化副标题
     */
    private fun initSubTitleView(parent: LinearLayout, array: TypedArray) {

        val text = array.getString(R.styleable.XSuperTitleBar_titlebar_subtitle_text)
        val textSize =
            array.getDimension(
                R.styleable.XSuperTitleBar_titlebar_subtitle_text_size,
                getTitleBarStyle().getTitleTextSize()
            )
        val textColor =
            array.getColor(
                R.styleable.XSuperTitleBar_titlebar_subtitle_text_color,
                getTitleBarStyle().getTitleTextColor()
            )

        val itemView = AppCompatTextView(context).apply {
            id = R.id.titlebar_subtitle_view
            isSingleLine = true
            gravity = Gravity.CENTER
            ellipsize = TextUtils.TruncateAt.END
            setTextSize(COMPLEX_UNIT_PX, textSize)
            setTextColor(textColor)
            setOnClickListener(this@XSuperTitleBar)
        }

        val layoutParams = LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }

        parent.addView(itemView, layoutParams)

        //设置标题
        setSubTitleText(text)
    }

    /**
     * 初始化分割线
     */
    protected open fun initLineView(array: TypedArray) {
        val visible = array.getBoolean(R.styleable.XSuperTitleBar_titlebar_line_visible, true)
        val drawable = array.getDrawable(R.styleable.XSuperTitleBar_titlebar_line_background)
            ?: getTitleBarStyle().getLineDrawable()
        val height = array.getDimensionPixelOffset(
            R.styleable.XSuperTitleBar_titlebar_line_height,
            getTitleBarStyle().getLineHeight() ?: 1
        )

        val itemView = View(context).apply {
            background = drawable
            visibility = if (visible) View.VISIBLE else View.GONE
        }
        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            height
        ).apply {
            gravity = Gravity.BOTTOM
        }
        addView(itemView, layoutParams)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = measureHeight(heightMeasureSpec)
        val heightSpan = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, heightSpan)
    }

    protected open fun measureHeight(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        var specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.AT_MOST) {
            specSize = getTitleBarStyle().getHeight()
        }
        return specSize
    }

    /**
     * 获取左条目
     */
    open fun getLeftView(): AppCompatTextView {
        return findViewById(R.id.titlebar_left_view)
    }

    /**
     * 获取右条目
     */
    open fun getRightView(): AppCompatTextView {
        return findViewById(R.id.titlebar_right_view)
    }

    /**
     * 获取标题条目
     */
    open fun getTitleView(): AppCompatTextView? {
        return findViewById(R.id.titlebar_title_view)
    }

    /**
     * 获取副标题条目
     */
    open fun getSubTitleView(): AppCompatTextView? {
        return findViewById(R.id.titlebar_subtitle_view)
    }

    /**
     * 获取分割线视图
     */
    open fun getLineView(): View? {
        return findViewById(R.id.titlebar_line_view)
    }

    /**
     * 设置标题
     */
    open fun setTitleText(@StringRes resId: Int): XSuperTitleBar {
        return setTitleText(content = context.getString(resId))
    }

    /**
     * 设置标题
     */
    open fun setTitleText(content: String?): XSuperTitleBar {
        getTitleView()?.apply {
            text = content
            visibility = if (content.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
        onTitleContentChange()
        return this
    }

    /**
     * 设置标题
     */
    open fun setSubTitleText(@StringRes resId: Int): XSuperTitleBar {
        return setSubTitleText(content = context.getString(resId))
    }

    /**
     * 设置副标题
     */
    open fun setSubTitleText(content: String?): XSuperTitleBar {
        getSubTitleView()?.apply {
            text = content
            visibility = if (content.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
        onTitleContentChange()
        return this
    }

    /**
     * 设置标题文本大小
     */
    open fun setTitleTextSize(unit: Int = COMPLEX_UNIT_PX, size: Float): XSuperTitleBar {
        getTitleView()?.setTextSize(unit, size)
        return this
    }

    /**
     * 设置标题文本大小
     */
    open fun setSubTitleTextSize(unit: Int = COMPLEX_UNIT_PX, size: Float): XSuperTitleBar {
        getSubTitleView()?.setTextSize(unit, size)
        return this
    }

    /**
     * 设置左标题
     */
    open fun setLeftText(@StringRes resId: Int): XSuperTitleBar {
        return setLeftText(content = context.getString(resId))
    }

    /**
     * 设置左标题
     */
    open fun setLeftText(content: String?): XSuperTitleBar {
        getLeftView().text = content
        return this
    }


    /**
     * 设置左图标
     */
    open fun setLeftIcon(drawable: Drawable?): XSuperTitleBar {
        getLeftView().setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        return this
    }

    /**
     * 设置左图标
     */
    open fun setLeftIcon(@DrawableRes resId: Int): XSuperTitleBar {
        return setLeftIcon(context.getDrawableById(resId))
    }

    /**
     * 设置左文本大小
     */
    open fun setLeftTextSize(unit: Int = COMPLEX_UNIT_PX, size: Float): XSuperTitleBar {
        getLeftView().setTextSize(unit, size)
        return this
    }

    /**
     * 设置右标题
     */
    open fun setRightText(@StringRes resId: Int): XSuperTitleBar {
        return setRightText(content = context.getString(resId))
    }

    /**
     * 设置右标题
     */
    open fun setRightText(content: String?): XSuperTitleBar {
        getRightView().text = content
        return this
    }

    /**
     * 设置右图标
     */
    open fun setRightIcon(drawable: Drawable?): XSuperTitleBar {
        getRightView().setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        return this
    }

    /**
     * 设置右图标
     */
    open fun setRightIcon(@DrawableRes resId: Int): XSuperTitleBar {
        return setRightIcon(context.getDrawableById(resId))
    }

    /**
     * 设置右文本大小
     */
    open fun setRightTextSize(unit: Int = COMPLEX_UNIT_PX, size: Float): XSuperTitleBar {
        getRightView().setTextSize(unit, size)
        return this
    }

    /**
     * 当前样式
     */
    open fun getTitleBarStyle(): ITitleBarStyle {
        if (titleBarStyle == null) {
            //默认样式
            titleBarStyle = DefaultTitleBarStyle(context.applicationContext)
        }
        return titleBarStyle!!
    }

    /**
     * 设置标题栏点击事件
     * @param listener 点击事件
     */
    fun setOnTitleBarClickListener(listener: OnTitleBarClickListener?) {
        this.onTitleClickListener = listener
        this.onLeftTitleClickListener = listener
        this.onRightTitleClickListener = listener
        this.onSubTitleClickListener = listener
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //标题
            R.id.titlebar_title_view -> {
                onTitleClickListener?.onTitleClick(v)
            }
            //副标题
            R.id.titlebar_subtitle_view -> {
                onSubTitleClickListener?.onSubTitleClick(v)
            }
            //左视图
            R.id.titlebar_left_view -> {
                onLeftTitleClickListener?.onLeftTitleClick(v)
            }
            //右视图
            R.id.titlebar_right_view -> {
                onRightTitleClickListener?.onRightTitleClick(v)
            }
        }
    }

    //标题内容被更新
    protected open fun onTitleContentChange() {

        if (!getTitleView()?.text.isNullOrEmpty() && !getSubTitleView()?.text.isNullOrEmpty()) {
            //都有内容时，设置间距
            setMargins(getSubTitleView()!!, top = titleMargin)
        } else {
            setMargins(getSubTitleView())
        }
    }

    //设置边距
    private fun setMargins(
        view: View?,
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0
    ) {
        (view?.layoutParams as? MarginLayoutParams)?.apply {
            setMargins(left, top, right, bottom)
        }
    }
}