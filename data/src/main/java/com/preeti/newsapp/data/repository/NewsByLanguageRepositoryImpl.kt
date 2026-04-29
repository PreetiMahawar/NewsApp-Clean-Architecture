package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsByLanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsByLanguageRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    NewsByLanguageRepository {

    override fun getNewsByLanguage(languageId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByLanguage(languageId))
        }.map {
            it.articles
        }
    }
}