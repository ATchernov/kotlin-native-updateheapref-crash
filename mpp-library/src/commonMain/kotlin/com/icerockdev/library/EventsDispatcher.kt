package com.icerockdev.library

expect class EventsDispatcher<ListenerType: kotlin.Any> {
    fun dispatchEvent(block: ListenerType.() -> Unit)
}
