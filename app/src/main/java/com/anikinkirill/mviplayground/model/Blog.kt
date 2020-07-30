package com.anikinkirill.mviplayground.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Blog(
    @SerializedName("pk")
    @Expose
    val id: Int? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null,

    @SerializedName("body")
    @Expose
    val body: String? = null
)