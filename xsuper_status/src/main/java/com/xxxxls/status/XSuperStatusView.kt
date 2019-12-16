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
class XSuperStatusView : RelativeLayout, IStatusView, View.OnClickListener {


    //当前类型
    protected var statusType: XStatus = XStatus.Default

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

    //状态改变事件
    protected var statusChangeListener: IStatusView.OnStatusChangeListener? = null

    //重试事件
    protected var retryClickListener: IStatusView.OnRetryClickListener? = null

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
                View.NO_ID
            )

        statusLayoutIds[XStatus.Error::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_error_layout,
                View.NO_ID
            )

        statusLayoutIds[XStatus.Loading::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_loading_layout,
                View.NO_ID
            )

        statusLayoutIds[XStatus.NoNetwork::class.java] =
            array.getResourceId(
                R.styleable.XSuperStatusView_status_no_network_layout,
                View.NO_ID
            )

        array.recycle()
    }

    /**
     * 设置更新状态
     */
    override fun switchStatus(status: XStatus) {
        if (this.statusType == status) {
            return
        }

        if (status == XStatus.Content) {
            switchContentView()
            changeStatus(status)
            return
        }

        checkGenerateView(status)?.let {
            switchView(it)
            changeStatus(status)
        } ?: let {
            //该状态无对应视图
        }
    }

    /**
     * 获取当前类型
     */
    override fun getStatus(): XStatus {
        return statusType
    }

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusView 状态视图
     */
    override fun addStatus(status: XStatus, statusView: View) {
        statusViews[status.javaClass] = statusView
        statusLayoutIds[status.javaClass] = statusView.id
    }

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusLayoutId 状态视图布局ID
     */
    override fun addStatus(status: XStatus, @LayoutRes statusLayoutId: Int) {
        statusLayoutIds[status.javaClass] = statusLayoutId
    }

    /**
     * 改变状态
     */
    private fun changeStatus(status: XStatus) {
        if (statusType != XStatus.Default) {
            statusChangeListener?.onChange(statusType, status)
        }
        this.statusType = status
    }

    /**
     * 检查构造view
     * @param status
     */
    private fun checkGenerateView(status: XStatus): View? {
        statusViews[status.javaClass]?.let { view ->
            return view
        } ?: let {
            statusLayoutIds[status.javaClass]?.let { layoutId ->
                if (layoutId <= 0) {
                    return null
                }
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
            view.findViewById<View>(R.id.status_retry_view)?.setOnClickListener(this)
            addView(view, 0, DEFAULT_LAYOUT_PARAMS)
        }
    }

    /**
     * 展示指定View 隐藏其他
     * @param view id
     */
    private fun switchView(view: View) {
        checkAddView(view)
        for (index in 0 until childCount) {
            getChildAt(index).apply {
                visibility = if (this == view) View.VISIBLE else View.GONE
            }
        }
    }

    /**
     * 展示内容View
     */
    private fun switchContentView() {
        checkGenerateView(XStatus.Content)?.let {
            switchView(it)
        } ?: let {
            for (index in 0 until childCount) {
                getChildAt(index).apply {
                    visibility = if (statusViews.containsValue(this)) View.GONE else View.VISIBLE
                }
            }
        }
    }

    fun showContent() {
        switchStatus(XStatus.Content)
    }

    fun showError() {
        switchStatus(XStatus.Error)
    }

    fun showLoading() {
        switchStatus(XStatus.Loading)
    }

    fun showEmpty() {
        switchStatus(XStatus.Empty)
    }

    fun showNoNetwork() {
        switchStatus(XStatus.NoNetwork)
    }

    override fun onClick(v: View?) {
        //重试点击事件
        if (this.statusType != XStatus.Default) {
            val isLoading = retryClickListener?.onRetry(this.statusType)
            isLoading?.apply {
                if (this) {
                    switchStatus(XStatus.Loading)
                }
            }
        }
    }

    override fun setOnStatusChangeListener(listener: IStatusView.OnStatusChangeListener?) {
        this.statusChangeListener = listener
    }

    override fun setOnRetryClickListener(listener: IStatusView.OnRetryClickListener?) {
        this.retryClickListener = listener
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        switchStatus(XStatus.Content)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        statusViews.clear()
        statusLayoutIds.clear()
        retryClickListener = null
        statusChangeListener = null
    }

}