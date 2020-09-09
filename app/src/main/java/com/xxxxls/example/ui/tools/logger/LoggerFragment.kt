package com.xxxxls.example.ui.tools.logger

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.IndexItemBean
import com.xxxxls.example.ui.indexs.BaseIndexFragment
import com.xxxxls.logger.LogConfiguration
import com.xxxxls.logger.XLogger
import com.xxxxls.logger.interceptor.BlacklistTagInterceptor
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.utils.date.DateFormat
import java.util.*

/**
 * 日志
 * @author Max
 * @date 2020-01-19.
 */
@Route(path = HomePaths.HOME_FRAGMENT_LOGGER_INDEX)
class LoggerFragment : BaseIndexFragment() {
    override fun onInitialize() {
        super.onInitialize()
        XLogger.init(
            LogConfiguration.newBuilder()
                //添加拦截器
                .addInterceptors(BlacklistTagInterceptor("BLACK-TAG1", "BLACK-TAG2"))
                //展示边框
                .showBorder()
                //设置堆栈深度
                .setStackTraceDepth(2)
                //展示线程信息
                .showThread().build()
        )
    }

    override fun getTitle(): String {
        return "LOGGER"
    }

    override fun getItems(): Array<IndexItemBean> {
        return arrayOf(IndexItemBean("D"), IndexItemBean("E"), IndexItemBean("BLACK-D"))
    }

    override fun onItemClick(index: Int, item: IndexItemBean) {
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