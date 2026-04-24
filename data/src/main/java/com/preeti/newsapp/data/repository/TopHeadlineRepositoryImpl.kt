package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class TopHeadlineRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    TopHeadlineRepository {

    override fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
}