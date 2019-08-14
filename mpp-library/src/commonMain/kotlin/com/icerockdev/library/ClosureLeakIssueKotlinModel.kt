package com.icerockdev.library

class AbstractItem {}

class ClosureLeakIssueKotlinModel(
    private val eventsDispatcher: EventsDispatcher<ClosureInterface>,
    private val itemsFactory: ItemsFactory) {

    fun getItems(): List<AbstractItem>{
        return (1..10).map { "Item #$it" }.map{ item ->
                itemsFactory.createDataItem(item) {
                    eventsDispatcher.dispatchEvent {
                        //MAYBE: 'this' is captured
                        itemTapped(item)
                    } }
        }
    }

    interface ItemsFactory {
        fun createDataItem(title: String, onTap: (() -> Unit)): AbstractItem
    }

    interface ClosureInterface {
        fun itemTapped(title: String)
    }
}

