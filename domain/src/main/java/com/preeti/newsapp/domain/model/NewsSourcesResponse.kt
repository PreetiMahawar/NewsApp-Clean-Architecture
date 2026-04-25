package com.preeti.newsapp.domain.model

import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val sources: List<NewsSource> = ArrayList()
)

