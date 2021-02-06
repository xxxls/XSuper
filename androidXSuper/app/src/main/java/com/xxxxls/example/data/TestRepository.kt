package com.xxxxls.example.data

import com.xxxxls.module_base.mvvm.BaseRepository
import com.xxxxls.utils.date.DateUtils
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * 首页数据
 * @author Max
 * @date 2019-12-25.
 */
class TestRepository @javax.inject.Inject constructor() : BaseRepository() {

    fun getTestDataA(time: Long): Flow<String> {
        return flowSafety {
            DateUtils.formatDateOfYMDHMS(Date(time))
        }
    }

    fun getTestDataB(): Flow<Long> {
        return flowSafety {
            System.currentTimeMillis()
        }
    }
}