package com.preeti.newsapp.domain.repository


import com.preeti.newsapp.domain.model.NewsSource
import kotlinx.coroutines.flow.Flow

interface NewsSourcesRepository {

     fun getNewsSources(): Flow<List<NewsSource>>

}
