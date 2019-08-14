package com.icerockdev.library

interface EventsDispatcher<ListenerType: kotlin.Any> {
    fun dispatchEvent(block: ListenerType.() -> Unit)
}
