package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.repository.NewsByCountryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsByCountryUseCase @Inject constructor(private val newsByCountryRepository: NewsByCountryRepository) {

    operator fun invoke(countryId: String): Flow<List<Article>> {
        return newsByCountryRepository.getNewsByCountry(countryId)
    }
}
