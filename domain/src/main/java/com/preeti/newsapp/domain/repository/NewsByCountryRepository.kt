package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsByCountryRepository {

    fun getNewsByCountry(country: String): Flow<List<Article>>

}