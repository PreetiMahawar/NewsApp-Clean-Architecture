package com.preeti.newsapp.presentation.newssources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preeti.newsapp.domain.dispacther.DispatcherProvider
import com.preeti.newsapp.domain.model.NewsSource
import com.preeti.newsapp.domain.usecase.GetNewsSourcesUseCase
import com.preeti.newsapp.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val getNewsSourcesUseCase: GetNewsSourcesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<NewsSource>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<NewsSource>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {
        viewModelScope.launch(dispatcherProvider.main) {
            getNewsSourcesUseCase()
                .flowOn(dispatcherProvider.io)
                .catch {
                    _uiState.value = UiState.Error(it.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}