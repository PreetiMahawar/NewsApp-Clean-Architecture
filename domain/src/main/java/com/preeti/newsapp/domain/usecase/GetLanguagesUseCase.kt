package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.Language
import com.preeti.newsapp.domain.repository.LanguagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLanguagesUseCase @Inject constructor(private val languagesRepository: LanguagesRepository){

    operator fun invoke(): Flow<List<Language>> {
        return languagesRepository.getLanguages()
    }

}