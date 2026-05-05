package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.helper.AssetsHelper
import com.preeti.newsapp.domain.model.Country
import com.preeti.newsapp.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(private val assetsHelper: AssetsHelper) : CountriesRepository {

    override fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(assetsHelper.getCountries())
        }.map {
            it.countries
        }
    }
}