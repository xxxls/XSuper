package com.xxxxls.xsuper.net

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * ViewModel 工厂
 * @author Max
 * @date 2019-11-30.
 */
class ViewModelFactory<VM : XSuperViewModel<*>>(private val clazz: Class<VM>) :
    ReadOnlyProperty<ViewModelStoreOwner, VM> {

    override fun getValue(thisRef: ViewModelStoreOwner, property: KProperty<*>): VM {
        return ViewModelProvider(thisRef).get(clazz)
    }

}
