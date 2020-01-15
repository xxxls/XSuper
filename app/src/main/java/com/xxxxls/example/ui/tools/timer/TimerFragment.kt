package com.xxxxls.example.ui.tools.timer

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.R
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.utils.L
import com.xxxxls.utils.TimerTask
import com.xxxxls.utils.ktx.*
import kotlinx.android.synthetic.main.fragment_timer.*

/**
 * 定时任务相关
 * @author Max
 * @date 2020-01-13.
 */
@Route(path = HomePaths.HOME_FRAGMENT_TIMER_INDEX)
class TimerFragment : BaseFragment() {

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_timer
    }

    private var countTimerTask: TimerTask? = null
    private var loopTimerTask: TimerTask? = null

    override fun onInitialize() {
        super.onInitialize()

        tv_count_start.singleClick {
            startCount()
        }
        tv_count_cancel.singleClick {
            countTimerTask?.cancel()
        }

        tv_loop_start.singleClick {
            startLoop()
        }

        tv_loop_cancel.singleClick {
            loopTimerTask?.cancel()
        }
    }

    private fun startCount() {
        countTimerTask = countTask(
            isVisiblePost = false,
            isDesc = true,
            totalCount = 10,
            countUnit = 1000,
            onTick = {
                tv_count.text = it.toString()
                L.e("tv_count -> onTick :$it")
            },
            onFinish = {
                tv_count.text = "结束了"
                L.e("tv_count -> onFinish")
            }
        )
    }

    private fun startLoop() {
        loopTimerTask = loopTask(
            isVisiblePost = true,
            countInterval = 500,
            onTick = {
                tv_loop.text = (it / 100).toString()
                L.e("tv_loop -> onTick :$it")
            }
        )
    }

}