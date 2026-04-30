package com.preeti.newsapp.presentation.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preeti.newsapp.domain.dispacther.DispatcherProvider
import com.preeti.newsapp.domain.model.Language
import com.preeti.newsapp.domain.usecase.GetLanguagesUseCase
import com.preeti.newsapp.domain.usecase.GetTwoLanguagesSelectUseCase
import com.preeti.newsapp.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TwoLanguagesViewModel @Inject constructor(
    private val getLanguagesUseCase: GetLanguagesUseCase,
    private val getTwoLanguagesSelectUseCase: GetTwoLanguagesSelectUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Language>>> = _uiState
    private val _selectedIds = MutableStateFlow<Set<String>>(emptySet())
    val selectedIds: StateFlow<Set<String>> = _selectedIds

    init {
        fetchLanguages()
    }

    fun onLanguageSelect(languageId: String) {
        val updatedSet = getTwoLanguagesSelectUseCase.selectLanguagesUseCase(languageId, selectedIds.value)
        _selectedIds.value = updatedSet
    }

    fun checkSelection(): Boolean {
        return getTwoLanguagesSelectUseCase.validateLanguagesUseCase(selectedIds.value)
    }

    private fun fetchLanguages() {
        viewModelScope.launch(dispatcherProvider.main) {
            getLanguagesUseCase()
                .flowOn(dispatcherProvider.io)
                .catch {
                _uiState.value = UiState.Error(it.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}