package com.preeti.newsapp.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectLanguagesUseCase @Inject constructor() {

    operator fun invoke(languageId: String, currentSet: Set<String>): Set<String> {
        val updatedSet = currentSet.toMutableSet()
        if (updatedSet.contains(languageId)) {
            updatedSet.remove(languageId)
        } else {
            if (updatedSet.size < 2) {
                updatedSet.add(languageId)
            }
        }
        return updatedSet
    }

}