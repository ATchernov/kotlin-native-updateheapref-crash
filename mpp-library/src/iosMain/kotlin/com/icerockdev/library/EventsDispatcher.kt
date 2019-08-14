package com.icerockdev.library

import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.native.ref.WeakReference

/**
 * Created by Aleksey Mikhailov <Aleksey.Mikhailov@icerockdev.com> on 12.12.2017.
 */

actual class EventsDispatcher<ListenerType: kotlin.Any>(mListener: ListenerType) {
    private val weakListener: WeakReference<ListenerType> = WeakReference(mListener)

    actual fun dispatchEvent(block: ListenerType.() -> Unit) {
        weakListener.get()?.let {
            val mainQueue = dispatch_get_main_queue()
            dispatch_async(mainQueue) {
                block(it)
            }
        }
    }
}
