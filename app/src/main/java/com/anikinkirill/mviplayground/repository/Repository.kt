package com.anikinkirill.mviplayground.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.anikinkirill.mviplayground.api.ApiResponse.*
import com.anikinkirill.mviplayground.api.RetrofitBuilder
import com.anikinkirill.mviplayground.ui.main.state.MainViewState
import com.anikinkirill.mviplayground.util.DataState

object Repository {

    fun getBlogs() : LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getBlogs()) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {
                        is ApiSuccessResponse -> {
                           value = DataState.data(data = MainViewState(blogs = apiResponse.body))
                        }
                        is ApiErrorResponse -> {
                            value = DataState.error(message = apiResponse.errorMessage)
                        }
                        is ApiEmptyResponse -> {
                            value = DataState.error(message = "Empty Response!")
                        }
                    }
                }
            }
        }
    }

    fun getUser(userId: String) : LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {
                        is ApiSuccessResponse -> {
                            value = DataState.data(data = MainViewState(user = apiResponse.body))
                        }
                        is ApiErrorResponse -> {
                            value = DataState.error(message = apiResponse.errorMessage)
                        }
                        is ApiEmptyResponse -> {
                            value = DataState.error(message = "Empty Response!")
                        }
                    }
                }
            }
        }
    }

}