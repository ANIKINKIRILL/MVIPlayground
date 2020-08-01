package com.anikinkirill.mviplayground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anikinkirill.mviplayground.model.Blog
import com.anikinkirill.mviplayground.model.User
import com.anikinkirill.mviplayground.repository.Repository
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent.*
import com.anikinkirill.mviplayground.ui.main.state.MainViewState
import com.anikinkirill.mviplayground.util.AbsentLiveData
import com.anikinkirill.mviplayground.util.DataState

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState> get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations.switchMap(_stateEvent) {
        handleStateEvent(it)
    }

    private fun handleStateEvent(stateEvent: MainStateEvent) : LiveData<DataState<MainViewState>> {
        return when(stateEvent) {
            is GetUserEvent -> {
                Repository.getUser(stateEvent.userId)
            }
            is GetBlogsEvent -> {
                Repository.getBlogs()
            }
            is Non -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setBlogsListData(blogs: List<Blog>) {
        val update = getCurrentViewStateOrNew()
        update.blogs = blogs
        _viewState.value = update
    }

    fun setUserData(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    private fun getCurrentViewStateOrNew() : MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }

}