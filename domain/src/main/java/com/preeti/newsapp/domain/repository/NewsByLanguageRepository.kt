package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsByLanguageRepository {

    fun getNewsByLanguage(languageId: String): Flow<List<Article>>

}