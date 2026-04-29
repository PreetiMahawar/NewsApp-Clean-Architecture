package com.preeti.newsapp.data.api

import com.preeti.newsapp.domain.model.NewsSourcesResponse
import com.preeti.newsapp.domain.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @GET("top-headlines")
    suspend fun getNewsBySourceName(@Query("sources") sources: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") language: String): TopHeadlinesResponse

}

