package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsBySourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsBySourceRepositoryImpl @Inject constructor(private val networkService: NetworkService) : NewsBySourceRepository {

    override fun getNewsBySourceName(sourceId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsBySourceName(sourceId))
        }.map {
            it.articles
        }
    }
}