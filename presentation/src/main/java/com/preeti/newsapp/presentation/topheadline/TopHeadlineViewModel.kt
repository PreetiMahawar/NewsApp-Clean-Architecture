package com.preeti.newsapp.presentation.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preeti.newsapp.domain.dispacther.DispatcherProvider
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.usecase.GetTopHeadlineUseCase
import com.preeti.newsapp.presentation.base.UiState
import com.preeti.newsapp.utils.AppConstant.COUNTRY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val getTopHeadlineUseCase: GetTopHeadlineUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchNews()
    }

    private fun fetchNews() {

        viewModelScope.launch(dispatcherProvider.main) {
            getTopHeadlineUseCase(COUNTRY)
                .flowOn(dispatcherProvider.io)
                .catch {
                    _uiState.value = UiState.Error(it.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}



