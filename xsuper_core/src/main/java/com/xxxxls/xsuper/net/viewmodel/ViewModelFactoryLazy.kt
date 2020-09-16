package com.xxxxls.xsuper.net.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.xxxxls.xsuper.component.IComponentViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * ViewModel 工厂
 * @author Max
 * @date 2019-11-30.
 */
class ViewModelFactoryLazy<VM : XSuperViewModel>(val initializer: () -> Class<VM>) :
    ReadOnlyProperty<IComponentViewModel, VM> {

    private var mViewModel: VM? = null

    override fun getValue(thisRef: IComponentViewModel, property: KProperty<*>): VM {
        if (mViewModel == null) {
            mViewModel = ViewModelProvider(thisRef).get(initializer())
            //建立与组件的关联
            thisRef.addViewModel(mViewModel)
        }
        return mViewModel!!
    }

}
