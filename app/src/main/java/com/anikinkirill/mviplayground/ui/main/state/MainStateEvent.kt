package com.anikinkirill.mviplayground.ui.main.state

sealed class MainStateEvent {

    class GetBlogsEvent : MainStateEvent()

    class GetUserEvent(
        val userId: String
    ) : MainStateEvent()

    class Non : MainStateEvent()

}