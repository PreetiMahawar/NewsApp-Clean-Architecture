package com.preeti.newsapp.domain.model

sealed interface SearchRequest {

    data class Valid(val query: String) : SearchRequest

    data object TooShort : SearchRequest
}

