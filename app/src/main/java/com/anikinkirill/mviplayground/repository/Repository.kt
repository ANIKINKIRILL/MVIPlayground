package com.anikinkirill.mviplayground.repository

import androidx.lifecycle.LiveData
import com.anikinkirill.mviplayground.api.ApiResponse
import com.anikinkirill.mviplayground.api.ApiResponse.ApiSuccessResponse
import com.anikinkirill.mviplayground.api.RetrofitBuilder
import com.anikinkirill.mviplayground.model.Blog
import com.anikinkirill.mviplayground.model.User
import com.anikinkirill.mviplayground.ui.main.state.MainViewState
import com.anikinkirill.mviplayground.util.DataState

object Repository {

    fun getBlogs() : LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<Blog>, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Blog>>) {
                result.value = DataState.data(data = MainViewState(blogs = response.body))
            }

            override fun createCall(): LiveData<ApiResponse<List<Blog>>> {
                return RetrofitBuilder.apiService.getBlogs()
            }

        }.asLiveData()
    }

    fun getUser(userId: String) : LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<User, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(data = MainViewState(user = response.body))
            }

            override fun createCall(): LiveData<ApiResponse<User>> {
                return RetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()
    }

}