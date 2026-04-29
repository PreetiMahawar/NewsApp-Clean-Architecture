package com.preeti.newsapp.presentation.newsbylanguage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preeti.newsapp.domain.dispacther.DispatcherProvider
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.usecase.GetNewsByLanguageUseCase
import com.preeti.newsapp.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsByLanguageViewModel @Inject constructor(
    private val newsByLanguageUseCase: GetNewsByLanguageUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun fetchNewsByLanguage(languageId: String) {

        viewModelScope.launch(dispatcherProvider.main) {
            newsByLanguageUseCase(languageId)
                .flowOn(dispatcherProvider.io)
                .catch {
                _uiState.value = UiState.Error(it.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}