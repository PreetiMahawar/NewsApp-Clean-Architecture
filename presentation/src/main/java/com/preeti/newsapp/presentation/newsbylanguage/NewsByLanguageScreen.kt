package com.preeti.newsapp.presentation.newsbylanguage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.base.CustomAppBarCenterAligned
import com.preeti.newsapp.presentation.topheadline.TopHeadlineScreen

@Composable
fun NewsByLanguageRoute(
    titleAppBar: String = stringResource(R.string.news_by_languages),
    onNewsClick: (url: String) -> Unit,
    onBackNavigation: () -> Unit,
    viewModel: NewsByLanguageViewModel = hiltViewModel(),
    languageId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.fetchNewsByLanguage(languageId)

    Scaffold(topBar = {
        CustomAppBarCenterAligned(
            title = titleAppBar, fontSize = 20.sp, onBackNavigation = onBackNavigation
        )
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineScreen(uiState, onNewsClick)
        }
    })
}