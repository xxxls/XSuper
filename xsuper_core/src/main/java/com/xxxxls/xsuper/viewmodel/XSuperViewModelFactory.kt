package com.xxxxls.xsuper.viewmodel

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.xxxxls.xsuper.component.IVmComponent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * ViewModel 工厂 （属性委托）（方式二）
 * @author Max
 * @date 2019-11-30.
 */
class ViewModelFactory<VM : XSuperViewModel>(private val clazz: KClass<VM>) :
    ReadOnlyProperty<IVmComponent, VM> {

    private var viewModel: VM? = null

    override fun getValue(thisRef: IVmComponent, property: KProperty<*>): VM {
        if (viewModel == null) {
            viewModel = ViewModelProvider(thisRef).get(clazz.java)
            //建立与组件的关联
            thisRef.addViewModel(viewModel!!)
        }
        return viewModel!!
    }
}


/**
 * 创建VM （方式二）
 */
@MainThread
inline fun <reified VM : XSuperViewModel> IVmComponent.xsuperViewModelsF(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
) = ViewModelFactory(VM::class)