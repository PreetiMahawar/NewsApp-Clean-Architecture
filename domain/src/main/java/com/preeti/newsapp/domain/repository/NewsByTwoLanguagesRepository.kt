package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsByTwoLanguagesRepository {

    fun getNewsByTwoLanguages(languageId1: String, languageId2: String): Flow<List<Article>>

}