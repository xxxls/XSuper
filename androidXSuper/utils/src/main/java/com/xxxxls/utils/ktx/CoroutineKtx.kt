package com.xxxxls.utils.ktx

import androidx.lifecycle.LifecycleOwner
import com.xxxxls.utils.CoroutineUtils
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


fun Any?.main(
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.main(start, block)
}

fun Any?.main(
    lifecycleOwner: LifecycleOwner,
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.main(lifecycleOwner, start, block)
}

fun <T> Any?.async(
    context: CoroutineContext = Dispatchers.IO,
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return CoroutineUtils.async(context, start, block)
}

fun Any?.io(
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.io(start, block)
}

fun Any?.io(
    lifecycleOwner: LifecycleOwner,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.io(lifecycleOwner, start, block)
}