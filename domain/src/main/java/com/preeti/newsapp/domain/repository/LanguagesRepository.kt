package com.preeti.newsapp.domain.repository

import com.preeti.newsapp.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface LanguagesRepository {

    fun getLanguages(): Flow<List<Language>>

}