package com.preeti.newsapp.domain.usecase

import com.preeti.newsapp.domain.model.SearchRequest
import com.preeti.newsapp.utils.AppConstant.DEBOUNCE_TIMEOUT
import com.preeti.newsapp.utils.AppConstant.MIN_SEARCH_CHAR
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchUseCase @Inject constructor() {
    @OptIn(FlowPreview::class)
    operator fun invoke(queryFlow: Flow<String>): Flow<SearchRequest> =
        queryFlow.debounce(DEBOUNCE_TIMEOUT).map { it.trim() }.distinctUntilChanged().map { query ->

                if (query.isNotBlank() && query.length >= MIN_SEARCH_CHAR) {
                    SearchRequest.Valid(query)
                } else {
                    SearchRequest.TooShort
                }
            }
}

