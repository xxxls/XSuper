package com.xxxxls.status

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes

/**
 * 多状态视图
 * @author Max
 * @date 2019-12-14.
 */
class XSuperStatusView : RelativeLayout {

    //当前类型
    protected var statusType: XStatus = XStatus.Content

    //状态view集合
    protected val statusViews: HashMap<Class<*>, View> by lazy {
        HashMap<Class<*>, View>()
    }

    //状态-id集合
    protected val statusLayoutIds: HashMap<Class<*>, Int> by lazy {
        HashMap<Class<*>, Int>()
    }

    //布局构造器
    protected val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    //默认布局参数
    private val DEFAULT_LAYOUT_PARAMS: RelativeLayout.LayoutParams by lazy {
        RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
    }

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
        val array =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.XSuperStatusView,
                defStyleAttr,
                defStyleAttr
            )

        statusLayoutIds[XStatus.Content::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_content_layout,
                View.NO_ID
            )

        statusLayoutIds[XStatus.Empty::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_empty_layout,
                R.id.status_empty_view
            )

        statusLayoutIds[XStatus.Error::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_error_layout,
                R.id.status_error_view
            )

        statusLayoutIds[XStatus.Loading::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_loading_layout,
                R.id.status_loading_view
            )

        statusLayoutIds[XStatus.NoNetWork::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_content_layout,
                R.id.status_no_network_view
            )

        array.recycle()
    }

    /**
     * 设置更新状态
     */
    fun setStatus(status: XStatus) {
        this.statusType = status
    }

    /**
     * 获取当前类型
     */
    fun getStatus(): XStatus {
        return statusType
    }

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusView 状态视图
     */
    fun addStatus(status: XStatus, statusView: View) {
        statusViews[status.javaClass] = statusView
        statusLayoutIds[status.javaClass] = statusView.id
    }

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusLayoutId 状态视图布局ID
     */
    fun addStatus(status: XStatus, @LayoutRes statusLayoutId: Int) {
        statusLayoutIds[status.javaClass] = statusLayoutId
    }

    /**
     * 切换状态
     * @param status 状态
     */
    fun switchStatus(status: XStatus) {
        if (this.statusType == status) {
            return
        }

        statusViews[status.javaClass]?.let { view ->
            checkAddView(view)
        } ?: let {
            statusLayoutIds[status.javaClass]?.let { layoutId ->
                statusViews[status.javaClass] = inflater.inflate(layoutId, null)
            }
        }
    }

    /**
     * 检查构造view
     * @param status
     */
    private fun checkGenerateView(status: XStatus): View? {
        statusViews[status.javaClass]?.let { view ->
            checkAddView(view)
            return view
        } ?: let {
            statusLayoutIds[status.javaClass]?.let { layoutId ->
                val statusView = inflater.inflate(layoutId, null)
                statusViews[status.javaClass] = statusView
                return statusView
            } ?: let {
                return null
            }
        }
    }

    /**
     * 检查添加view
     */
    private fun checkAddView(view: View) {
        if (indexOfChild(view) < 0) {
            addView(view, 0, DEFAULT_LAYOUT_PARAMS)
        }
    }

    /**
     * 展示指定ID的View 隐藏其他
     * @param viewId id
     */
    fun switchViewById(viewId: Int) {
        for (index in 0 until childCount) {
            getChildAt(index).apply {
                visibility = if (this.id == viewId) View.VISIBLE else View.GONE
            }
        }
    }

    /**
     * 展示内容View
     */
    fun switchContentView() {
        for (index in 0 until childCount) {
            getChildAt(index).apply {
                visibility = if (statusViews.containsValue(this)) View.GONE else View.VISIBLE
            }
        }
    }

    fun showContent() {

    }

    fun showError() {

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

}