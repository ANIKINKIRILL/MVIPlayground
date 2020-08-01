package com.anikinkirill.mviplayground.util

data class DataState<T>(
    var message: String? = null,
    var loading: Boolean = false,
    var data: T? = null
) {

    companion object {
        fun <T> error(message: String) : DataState<T> {
            return DataState(message, false, null)
        }

        fun <T> loading(isLoading: Boolean) : DataState<T> {
            return DataState(null, isLoading, null)
        }

        fun <T> data(message: String? = null, data: T? = null) : DataState<T> {
            return DataState(message, false, data)
        }
    }

}
