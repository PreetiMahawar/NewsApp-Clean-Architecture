package com.preeti.newsapp.data.repository

import com.preeti.newsapp.data.helper.AssetsHelper
import com.preeti.newsapp.domain.model.Language
import com.preeti.newsapp.domain.repository.LanguagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LanguagesRepositoryImpl @Inject constructor(private val assetsHelper: AssetsHelper) : LanguagesRepository {

    override fun getLanguages(): Flow<List<Language>> {
        return flow {
            emit(assetsHelper.getLanguages())
        }.map {
            it.languages
        }
    }
}