package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getNews(query: String): Flow<List<Article>>

}