package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsByTwoLanguagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTwoLanguagesUseCase @Inject constructor(val repository: NewsByTwoLanguagesRepository) {

    operator fun invoke(languageId1: String, languageId2: String): Flow<List<Article>> {
        return repository.getNewsByTwoLanguages(languageId1, languageId2)
    }

}