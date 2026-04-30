package com.preeti.newsapp.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateLanguagesUseCase @Inject constructor(){

    operator fun invoke(currentSet: Set<String>): Boolean {
        return currentSet.size == 2
    }

}