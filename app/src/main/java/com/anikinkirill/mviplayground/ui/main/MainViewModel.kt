package com.anikinkirill.mviplayground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent.*
import com.anikinkirill.mviplayground.ui.main.state.MainViewState
import com.anikinkirill.mviplayground.util.AbsentLiveData

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState> get() = _viewState

    val dataState: LiveData<MainStateEvent> = Transformations.switchMap(_stateEvent) {
        handleStateEvent(it)
    }


    private fun handleStateEvent(stateEvent: MainStateEvent) : LiveData<MainStateEvent> {
        return when(stateEvent) {
            is GetUserEvent -> {
                AbsentLiveData.create()
            }
            is GetBlogsEvent -> {
                AbsentLiveData.create()
            }
            is Non -> {
                AbsentLiveData.create()
            }
        }
    }

}