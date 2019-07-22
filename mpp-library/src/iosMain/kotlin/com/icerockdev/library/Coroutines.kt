package com.icerockdev.library

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext

/**
 * Created by Aleksey Mikhailov <Aleksey.Mikhailov@icerockdev.com> on 2019-07-22.
 */
class UIDispatcher : CoroutineDispatcher() {
    private val mQueue = dispatch_get_main_queue()

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(mQueue) {
            block.run()
        }
    }
}

actual val Dispatchers.UI: CoroutineDispatcher by lazy { UIDispatcher() }
