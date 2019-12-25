package com.xxxxls.example.net

import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.net.BaseApiRepository
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import kotlinx.coroutines.delay

/**
 *
 * @author Max
 * @date 2019-12-25.
 */
class HomeRepository : BaseApiRepository<HomeApis>(HomeApis::class.java) {

    /**
     * 模拟获取测试数据
     */
    fun getTestPagingList(callback:XSuperCallBack<TestPagingBean>){
        launch {
            delay(((500..3000).random()).toLong())
//            callback.
        }
    }
}