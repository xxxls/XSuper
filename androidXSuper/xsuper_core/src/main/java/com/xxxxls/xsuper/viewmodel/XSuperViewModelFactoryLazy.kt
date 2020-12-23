package com.xxxxls.xsuper.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.xxxxls.xsuper.component.IVmComponent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * ViewModel 工厂
 * @author Max
 * @date 2019-11-30.
 */
@Deprecated("没什么必要")
class XSuperViewModelFactoryLazy<VM : XSuperViewModel>(val initializer: () -> Class<VM>) :
    ReadOnlyProperty<IVmComponent, VM> {

    private var mViewModel: VM? = null

    override fun getValue(thisRef: IVmComponent, property: KProperty<*>): VM {
        if (mViewModel == null) {
            mViewModel = ViewModelProvider(thisRef).get(initializer())
            //建立与组件的关联
            thisRef.addViewModel(mViewModel)
        }
        return mViewModel!!
    }

}
