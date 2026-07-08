package com.preeti.newsapp.domain.usecase

import android.util.Log
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchNewsUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(query: String): Flow<List<Article>> {
        Log.d("SearchViewModel","inside search news")
        return searchRepository.getNews(query)
    }

}