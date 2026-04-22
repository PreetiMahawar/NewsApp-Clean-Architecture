package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface TopHeadlineRepository {

    fun getTopHeadlines(country: String): Flow<List<Article>>

}