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
class ViewModelFactory<VM : XSuperViewModel>(private val clazz: Class<VM>) :
    ReadOnlyProperty<IComponentViewModel, VM> {

    private var viewModel: VM? = null

    override fun getValue(thisRef: IComponentViewModel, property: KProperty<*>): VM {
        if (viewModel == null) {
            viewModel = ViewModelProvider(thisRef).get(clazz)
            //建立与组件的关联
            thisRef.addViewModel(viewModel!!)
        }
        return viewModel!!
    }

}
