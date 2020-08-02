package com.anikinkirill.mviplayground.ui

import com.anikinkirill.mviplayground.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>)

}