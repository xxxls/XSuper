package com.xxxxls.example.ui.test.flow

import androidx.hilt.lifecycle.ViewModelInject
import com.xxxxls.example.data.TestRepository
import com.xxxxls.module_base.mvvm.BaseLiveData
import com.xxxxls.module_base.mvvm.BaseViewModel
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

/**
 * Created by Max on 2/6/21 2:26 PM
 * Desc:
 */
class TestFlowViewModel @ViewModelInject constructor(val repository: TestRepository) : BaseViewModel() {

    val testLiveData: BaseLiveData<String> by lazy {
        BaseLiveData()
    }

    fun test1() {
        launchF(testLiveData) {
            repository.getTestDataB().map {
                10000L
            }.flatMapMerge {
                repository.getTestDataA(it).map {
                    "$it-MAP"
                }
            }
        }
    }
}