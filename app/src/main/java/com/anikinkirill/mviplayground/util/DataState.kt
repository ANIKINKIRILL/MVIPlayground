package com.anikinkirill.mviplayground.util

data class DataState<T>(
    var message: Event<String>? = null,
    var loading: Boolean = false,
    var data: Event<T>? = null
) {

    companion object {
        fun <T> error(message: String) : DataState<T> {
            return DataState(Event.messageEvent(message), false, null)
        }

        fun <T> loading(isLoading: Boolean) : DataState<T> {
            return DataState(null, isLoading, null)
        }

        fun <T> data(message: String? = null, data: T? = null) : DataState<T> {
            return DataState(Event.messageEvent(message), false, Event.dataEvent(data))
        }
    }

}
