package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTopHeadlineUseCase @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) {

    operator fun invoke(country: String): Flow<List<Article>> {
        return topHeadlineRepository.getTopHeadlines(country)
    }

}