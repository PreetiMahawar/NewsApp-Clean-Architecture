package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsByLanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsByLanguageUseCase @Inject constructor(private val newsByLanguageRepository: NewsByLanguageRepository) {

    operator fun invoke(languageId: String): Flow<List<Article>> {
        return newsByLanguageRepository.getNewsByLanguage(languageId)
    }
}