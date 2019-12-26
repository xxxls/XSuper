package com.xxxxls.example.net

import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.net.BaseApiRepository
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.xsuper.exceptions.NetWorkException
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import kotlinx.coroutines.delay

/**
 * 首页数据
 * @author Max
 * @date 2019-12-25.
 */
class HomeRepository : BaseApiRepository<HomeApis>(HomeApis::class.java) {

    /**
     * 模拟获取测试数据
     * @param isBefore 是否前面的数据
     * @param key
     * @param callback 回调
     */
    fun getTestPagingList(
        isBefore: Boolean,
        key: Int,
        callback: XSuperCallBack<ListResponse<TestPagingBean>>
    ) {
        launch {
            delay(((500..4000).random()).toLong())

            val random = (1..10).random()
            if (random > 8) {
                //模拟网络异常
                callback.onError(NetWorkException("模拟网络连接失败"))
                return@launch
            }

            if (random > 7) {
                //模拟空数据
                callback.onSuccess(
                    ListResponse(
                        curPage = key,
                        datas = ArrayList(),
                        offset = 0,
                        over = false,
                        pageCount = 0,
                        size = 0,
                        total = 0
                    )
                )
            }

            //成功响应
            if (isBefore) {
                callback.onSuccess(getAfterData(key))
            } else {
                callback.onSuccess(getBeforeData(key))
            }
        }
    }

    /**
     * 往后加载的数据
     */
    private fun getAfterData(key: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val startIndex = key + 1
        val endIndex = startIndex + 20

        for (index in startIndex until endIndex) {
            list.add(TestPagingBean(index, "author:$key", "item#$index"))
        }

        return ListResponse(key, list, 0, false, 5, list.size, 5 * 20)
    }


    /**
     * 往前加载数据
     */
    private fun getBeforeData(key: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val startIndex = key + (20 * -1) + 1

        for (index in startIndex until key) {
            list.add(TestPagingBean(index, "author:$key", "item#$index"))
        }

        return ListResponse(key, list, 0, false, 5, list.size, 5 * 20)
    }
}