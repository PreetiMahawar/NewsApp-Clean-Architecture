package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.domain.model.NewsSource
import com.preeti.newsapp.domain.repository.NewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourcesRepositoryImpl @Inject constructor (private val networkService: NetworkService) : NewsSourcesRepository {

    override fun getNewsSources(): Flow<List<NewsSource>> {
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            it.sources
        }
    }

}