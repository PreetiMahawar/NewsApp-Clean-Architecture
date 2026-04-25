package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsBySourceRepository {

    fun getNewsBySourceName(sourceId: String): Flow<List<Article>>

}