package com.preeti.newsapp.presentation.topheadline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.preeti.newsapp.presentation.base.CustomAppBarCenterAligned

@Composable
fun TopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    onBackNavigation: () -> Unit,
    viewModel: TopHeadlineViewModel = hiltViewModel(),
    titleAppBar: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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




