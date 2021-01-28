package com.xxxxls.example.data

import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.constants.Constants
import com.xxxxls.module_base.mvvm.BaseRepository
import com.xxxxls.module_base.network.response.BaseResponse
import com.xxxxls.module_base.network.response.IListResponse
import com.xxxxls.module_base.network.response.ListResponse
import com.xxxxls.xsuper.exceptions.NetWorkException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 首页数据
 * @author Max
 * @date 2019-12-25.
 */
class HomeRepository @javax.inject.Inject constructor() : BaseRepository() {

    /**
     * 模拟获取测试数据
     * @param isBefore 是否前面的数据
     * @param key
     * @param isRealSimulate 是否真实模拟(网络错误，空数据)
     */
    suspend fun getTestList(
        isBefore: Boolean,
        key: Int,
        isRealSimulate: Boolean
    ): Flow<IListResponse<TestPagingBean>> {
        return flow<IListResponse<TestPagingBean>> {
            delay(((200..2000).random()).toLong())
            if (isRealSimulate) {
                val random = (0..2).random()
                if (random == 2) {
                    logE("getTestList() 模拟网络连接失败")
                    //模拟网络异常
                    throw NetWorkException("模拟网络连接失败")
                }
                if (random == 1) {
                    //模拟空数据
                    logE("getTestList() 模拟空数据")
                    emit(
                        ListResponse(
                            curPage = key,
                            datas = ArrayList(),
                            offset = 0,
                            over = false,
                            pageCount = 0,
                            size = 0,
                            total = 0,
                            hasNextPage = false
                        )
                    )
                    return@flow
                }
            }

            val hasNextPage = (0..1).random() != 0
            logE("getTestList() 成功响应 hasNextPage:$hasNextPage")
            //成功响应
            if (isBefore) {
                emit(getBeforeData(key, hasNextPage))
            } else {
                emit(getAfterData(key, hasNextPage))
            }
        }
    }

    /**
     * 往后加载的数据
     */
    private fun getAfterData(key: Int, hasNextPage: Boolean): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val startIndex = key + 1
        val endIndex = startIndex + Constants.PAGE_SIZE

        for (index in startIndex until endIndex) {
            list.add(TestPagingBean(index, "content:$key", "item#$index"))
        }

        return ListResponse(
            key, list, 0, false, 5, list.size, 5 * Constants.PAGE_SIZE,
            hasNextPage = hasNextPage
        )
    }


    /**
     * 往前加载数据
     */
    private fun getBeforeData(key: Int, hasNextPage: Boolean): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val startIndex = key + (Constants.PAGE_SIZE * -1)

        for (index in startIndex until key) {
            list.add(TestPagingBean(index, "content:$key", "item#$index"))
        }


        return ListResponse(
            key,
            list,
            0,
            false,
            5,
            list.size,
            5 * Constants.PAGE_SIZE,
            hasNextPage = hasNextPage
        )
    }
}