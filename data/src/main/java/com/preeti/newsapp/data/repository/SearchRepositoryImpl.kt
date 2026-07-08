package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(private val networkService: NetworkService) : SearchRepository {

    override fun getNews(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsBySearch(query))
        }.map {
            it.articles
        }
    }
}