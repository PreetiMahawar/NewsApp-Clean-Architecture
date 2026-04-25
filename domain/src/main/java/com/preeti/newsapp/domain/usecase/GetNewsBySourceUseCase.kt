package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsBySourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsBySourceUseCase @Inject constructor (private val newsBySourceRepository: NewsBySourceRepository) {

    operator fun invoke(sourceId: String): Flow<List<Article>> {
        return newsBySourceRepository.getNewsBySourceName(sourceId)
    }
}