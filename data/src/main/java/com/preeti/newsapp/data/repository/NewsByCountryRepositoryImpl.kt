package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsByCountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsByCountryRepositoryImpl @Inject constructor(private val networkService: NetworkService) : NewsByCountryRepository {

    override fun getNewsByCountry(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
}