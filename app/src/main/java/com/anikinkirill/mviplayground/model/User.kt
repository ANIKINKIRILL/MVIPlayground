package com.anikinkirill.mviplayground.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("username")
    @Expose
    val username: String? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null

)