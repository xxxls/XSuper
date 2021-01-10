package com.xxxxls.example.ui.tools.logger

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.logger.XLogger
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.utils.date.DateFormat
import java.util.*

/**
 * 日志
 * @author Max
 * @date 2020-01-19.
 */
@Route(path = HomePaths.HOME_FRAGMENT_LOGGER_INDEX)
class LoggerFragment : BaseListFragment() {
    override fun onInitialize() {
        super.onInitialize()
        XLogger.init(XLogger.createDefaultConfig().apply {
            // 黑名单
            this.blackListTagList = arrayListOf("BLACK-TAG1", "BLACK-TAG2")
        })
    }

    override fun getTitle(): String {
        return "LOGGER"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(SimpleItemBean("D"), SimpleItemBean("E"), SimpleItemBean("BLACK-D"))
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                XLogger.d(message = "当前详情时间：${DateFormat.YMD_HMS_SS.formatDate(Date())}")
            }
            1 -> {
                XLogger.e(message = "当前时分秒：${DateFormat.HMS.formatDate(Date())}")
            }
            2 -> {
                XLogger.d(
                    tag = "BLACK-TAG1",
                    message = "当前时分：${DateFormat.HM.formatDate(Date())}"
                )
            }
        }
    }
}