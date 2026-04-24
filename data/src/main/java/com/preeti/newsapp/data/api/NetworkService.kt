package com.preeti.newsapp.data.api

import com.preeti.newsapp.domain.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

}

