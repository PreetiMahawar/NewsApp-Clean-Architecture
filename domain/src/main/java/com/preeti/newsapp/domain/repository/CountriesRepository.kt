package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    fun getCountries(): Flow<List<Country>>

}