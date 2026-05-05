package com.preeti.newsapp.presentation.newsbycountry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preeti.newsapp.domain.dispacther.DispatcherProvider
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.usecase.GetNewsByCountryUseCase
import com.preeti.newsapp.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsByCountryViewModel @Inject constructor(
    private val getNewsByCountryUseCase: GetNewsByCountryUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun fetchNewsByCountry(countryId: String) {

        viewModelScope.launch(dispatcherProvider.main) {
            getNewsByCountryUseCase(countryId)
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