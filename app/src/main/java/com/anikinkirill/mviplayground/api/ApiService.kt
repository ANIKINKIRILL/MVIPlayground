package com.anikinkirill.mviplayground.api

import com.anikinkirill.mviplayground.model.Blog
import com.anikinkirill.mviplayground.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("user/{userId}")
    fun getUser(@Path("userId") userId: String) : User

    @GET("blogs")
    fun getBlogs() : List<Blog>

}