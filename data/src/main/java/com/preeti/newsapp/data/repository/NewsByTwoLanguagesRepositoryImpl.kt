package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.api.NetworkService
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsByTwoLanguagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class NewsByTwoLanguagesRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    NewsByTwoLanguagesRepository {

    override fun getNewsByTwoLanguages(
        languageId1: String, languageId2: String
    ): Flow<List<Article>> {

        return getNewsByLanguage(languageId1).catch {
                emitAll(flowOf(emptyList()))
            }.zip(getNewsByLanguage(languageId2).catch {
                    emitAll(flowOf(emptyList()))
                }) { newsLanguage1, newsLanguage2 ->
                val allNews = arrayListOf<Article>()
                allNews.addAll(newsLanguage1)
                allNews.addAll(newsLanguage2)
                allNews.shuffle()
                return@zip allNews
            }
    }

    private fun getNewsByLanguage(languageId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByLanguage(languageId))
        }.map {
            it.articles
        }
    }
}