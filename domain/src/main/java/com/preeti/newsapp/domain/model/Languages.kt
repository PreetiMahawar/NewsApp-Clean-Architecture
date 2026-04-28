package com.preeti.newsapp.domain.model

import com.google.gson.annotations.SerializedName

data class Languages(
    @SerializedName("languages")
    val languages: List<Language> = ArrayList()
)
