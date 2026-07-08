package com.preeti.newsapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preeti.newsapp.domain.dispacther.DispatcherProvider
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.model.SearchRequest
import com.preeti.newsapp.domain.usecase.GetSearchUseCase
import com.preeti.newsapp.domain.usecase.SearchNewsUseCase
import com.preeti.newsapp.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase,
    private val searchNewsUseCase: SearchNewsUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val query = MutableStateFlow("")


    init {
        createNewsFlow()
    }

    fun searchNews(searchQuery: String) {
        query.value = searchQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun createNewsFlow() {
        viewModelScope.launch(dispatcherProvider.main) {
            getSearchUseCase(query).flatMapLatest { searchRequest ->
                when (searchRequest) {
                    is SearchRequest.Valid -> {
                        _uiState.value = UiState.Loading
                        searchNewsUseCase(searchRequest.query)
                    }

                    is SearchRequest.TooShort -> {
                        flowOf(emptyList())
                    }
                }
            }.flowOn(dispatcherProvider.io).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect { articles ->
                _uiState.value = UiState.Success(articles)
            }
        }
    }
}




