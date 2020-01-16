package com.xxxxls.adapter

/**
 * 预加载Adapter
 * @author Max
 * @date 2020-01-16.
 */
interface XSuperAdapterPreloadEngine<T, VH : XSuperViewHolder> : XSuperAdapterEngine<T, VH> {

    /**
     * 是否启用顶部预加载
     */
    fun isTopPreloadEnable(): Boolean

    /**
     * 是否启用底部预加载
     */
    fun isBottomPreloadEnable(): Boolean

    /**
     * 顶部预加载数量
     */
    fun getTopPreloadNumber(): Int

    /**
     * 底部预加载数量
     */
    fun getBottomPreloadNumber(): Int

    override fun xBindViewHolder(holder: VH, position: Int) {
        super.xBindViewHolder(holder, position)
        autoLoad(position)
    }

    override fun xBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        super.xBindViewHolder(holder, position, payloads)
        autoLoad(position)
    }

    /**
     * 字段加载
     */
    private fun autoLoad(position: Int){
        //TODO 后续完善
    }
}