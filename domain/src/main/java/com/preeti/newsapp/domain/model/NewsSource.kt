package com.preeti.newsapp.domain.model

import com.google.gson.annotations.SerializedName

data class NewsSource(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
)

