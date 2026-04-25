package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.NewsSource
import com.preeti.newsapp.domain.repository.NewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsSourcesUseCase @Inject constructor (private val newsSourcesRepository: NewsSourcesRepository) {

    operator fun invoke(): Flow<List<NewsSource>> {
        return newsSourcesRepository.getNewsSources()
    }

}