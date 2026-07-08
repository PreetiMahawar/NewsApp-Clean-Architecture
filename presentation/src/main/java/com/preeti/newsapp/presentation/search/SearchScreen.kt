package com.preeti.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.base.CustomAppBarCenterAligned
import com.preeti.newsapp.presentation.topheadline.TopHeadlineScreen

@Composable
fun SearchRoute(
    titleAppBar: String = stringResource(R.string.search),
    onNewsClick: (url: String) -> Unit,
    onBackNavigation: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        CustomAppBarCenterAligned(
            title = titleAppBar, fontSize = 20.sp, onBackNavigation = onBackNavigation
        )
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NewsSearchBar(viewModel, query, expanded)
            TopHeadlineScreen(uiState, onNewsClick)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSearchBar(
    viewModel: SearchViewModel, query: MutableState<String>, expanded: MutableState<Boolean>
) {
    DockedSearchBar(
        inputField = {
        SearchBarDefaults.InputField(
            query = query.value, onQueryChange = {
            query.value = it
            viewModel.searchNews(it)
        }, onSearch = {}, expanded = false, onExpandedChange = {
            expanded.value = false
        }, placeholder = { Text(stringResource(R.string.search_news_1)) },

            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.outline_search),
                    contentDescription = stringResource(R.string.search_icon)
                )
            }, trailingIcon = {
                if (query.value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            query.value = ""
                            viewModel.searchNews(query.value)
                        }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_close),
                            contentDescription = stringResource(R.string.clear_search)
                        )
                    }
                }
            })
    },
        expanded = false,
        onExpandedChange = { },
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, bottom = 14.dp, top = 14.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {

    }
}