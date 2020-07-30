package com.anikinkirill.mviplayground.ui.main.state

import com.anikinkirill.mviplayground.model.Blog
import com.anikinkirill.mviplayground.model.User

data class MainViewState(

    var blogs: List<Blog>? = null,
    var user: User? = null

)

