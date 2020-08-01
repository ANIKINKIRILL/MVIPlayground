package com.anikinkirill.mviplayground.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.anikinkirill.mviplayground.api.ApiResponse
import com.anikinkirill.mviplayground.api.ApiResponse.*
import com.anikinkirill.mviplayground.util.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultObject, ViewStateType> {

    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)

        GlobalScope.launch(IO) {
            delay(2000L)
            withContext(Main) {
                val apiResponse = createCall()
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    handleNetworkCall(response)
                }
            }
        }

    }

    private fun handleNetworkCall(response: ApiResponse<ResultObject>) {
        when(response) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse -> {
                onErrorResponse(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                onErrorResponse("Empty Response!")
            }
        }
    }

    private fun onErrorResponse(message: String) {
        result.value = DataState.error(message)
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResultObject>)

    abstract fun createCall() : LiveData<ApiResponse<ResultObject>>

    fun asLiveData() = result

}