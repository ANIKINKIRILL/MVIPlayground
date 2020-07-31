package com.anikinkirill.mviplayground.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.anikinkirill.mviplayground.api.ApiResponse.*
import com.anikinkirill.mviplayground.api.RetrofitBuilder
import com.anikinkirill.mviplayground.ui.main.state.MainViewState

object Repository {

    fun getBlogs() : LiveData<MainViewState> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getBlogs()) { apiResponse ->
            object : LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {
                        is ApiSuccessResponse -> {
                           value = MainViewState(
                               blogs = apiResponse.body
                           )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState()
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState()
                        }
                    }
                }
            }
        }
    }

    fun getUser(userId: String) : LiveData<MainViewState> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
            object : LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    when(apiResponse) {
                        is ApiSuccessResponse -> {
                            value = MainViewState(
                                user = apiResponse.body
                            )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState()
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState()
                        }
                    }
                }
            }
        }
    }

}