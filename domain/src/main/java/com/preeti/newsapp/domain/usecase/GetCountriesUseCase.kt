package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.Country
import com.preeti.newsapp.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCountriesUseCase @Inject constructor(private val countriesRepository: CountriesRepository) {

    operator fun invoke(): Flow<List<Country>> {
        return countriesRepository.getCountries()
    }

}