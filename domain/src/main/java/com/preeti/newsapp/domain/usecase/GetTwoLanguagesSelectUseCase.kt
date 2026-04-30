package com.preeti.newsapp.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class GetTwoLanguagesSelectUseCase @Inject constructor(
    val selectLanguagesUseCase: SelectLanguagesUseCase,
    val validateLanguagesUseCase: ValidateLanguagesUseCase
)